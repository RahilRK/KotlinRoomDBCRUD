package com.example.kotlinroomdbcrud.ffmpeg.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.arthenica.mobileffmpeg.*
import com.example.kotlinroomdbcrud.BuildConfig
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.ffmpeg.FfmpegActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class DoBlurWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val TAG = "DoBlurWorker"

    private val default_notification_channel_id =
        "${BuildConfig.APPLICATION_ID}_fcm_default_channel"
    private val default_notification_channel_name = "Normal Notification"

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
    var notification = NotificationCompat.Builder(context, default_notification_channel_id)

    //related to file path & url
    private var path = ""
    private var fontFilePath: File? = null
    private val timeStamp: String = SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(Date())
    private val videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
    private val qrcode_imagePath =
        "https://s3stagingpipeline.theweedtube.com/uploads/qr_code/415934/1663774186415934.jpg"
    private val pipeline_imagePath = "https://s3staging.theweedtube.com/uploads/safe-save/safe-save.png"

    //related to percentage
    private var videoTotalLength = 0
    private var lastPer = 0
    private var calculatePercentage = 0
    var halfProcessDone: Boolean = false

    /*val videoFile = File("/storage/emulated/0/Download/4_4615941674562787346_Video_5d2be832f91bac0f29771454a9145d3c.mp")
    val qrcode_imagePath = "/storage/emulated/0/Download/overlay_img.jpg"
    val pipeline_imagePath = "/storage/emulated/0/Download/Pipeline.png"*/

    override suspend fun doWork(): Result {

        try {
            Log.d(TAG, "doWork: is called")

            setForeground(
                createAndUpdateNotification(
                    "Hold on, working on it",
                    "Working on it",
                    "Please wait...",
                    0
                )
            )

            //start process
            deleteFont()
            getVideoLength()

        } catch (e: Exception) {
            Result.failure()
        }

        return Result.success()
    }

    private fun deleteFont() {

        try {
            val file =File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "twt_font.ttf"
            )
            file.delete()
        }
        catch (e: Exception) {

            val error = Log.getStackTraceString(e)
            Log.d(TAG, "deleteFont: $error")
        }
    }

    private fun createAppNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                default_notification_channel_id,
                default_notification_channel_name,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    private fun createAndUpdateNotification(
        title: String,
        ticker: String,
        progressText: String,
        progressPerc: Int
    ): ForegroundInfo {

        createAppNotificationChannel()

        notification
            .setProgress(100, progressPerc, false)
            .setContentTitle(title)
            .setTicker(ticker)
            .setContentText(progressText)
            .setSmallIcon(R.drawable.ic_baby_changing_station)
            .setSilent(true)
            .setOngoing(true).priority = NotificationCompat.PRIORITY_HIGH

        return ForegroundInfo(101, notification.build())
    }

    private suspend fun getVideoLength() {

        try {
            val info = FFprobe.getMediaInformation(videoUrl)
            val toMilliSecond = (info.duration.toDouble()) * 1000
            videoTotalLength = toMilliSecond.toInt() * 2
            Log.d(TAG, "getVideoLength: ${videoTotalLength}")

            runBlurCommand()

        } catch (e: Exception) {
            val error = Log.getStackTraceString(e)
            Log.d(TAG, error)
        }
    }

    private suspend fun getProgress() {

        Config.enableStatisticsCallback { statistics ->
            if (statistics != null) {
                /*Log.d(
                    TAG,
                    "statistics_detail: time: ${statistics.time}, frame: ${statistics.videoFrameNumber}, fps: ${statistics.videoFps}"
                )*/
                CoroutineScope(Dispatchers.IO).launch {

                    if (halfProcessDone) {
                        Log.d(TAG, "lastPer more than 50: ${statistics.time}")
                        calculatePercentage = statistics.time * 100 / videoTotalLength + lastPer
                    } else {
                        calculatePercentage = statistics.time * 100 / videoTotalLength
                        lastPer = calculatePercentage
                    }
                    Log.d(TAG, "lastPer: $lastPer")
                    Log.d(TAG, "calculatePercentage: $calculatePercentage%")

                    setForeground(
                        createAndUpdateNotification(
                            "Hold on, working on it",
                            "Working on it.",
                            "${calculatePercentage}%",
                            calculatePercentage
                        )
                    )
                }
            }
        }
    }

    private suspend fun runBlurCommand() {

        getProgress()

        val mVideoName = "Output_$timeStamp.mp4"
        val inputPath = videoUrl
        val outPath = "/storage/emulated/0/Documents/$mVideoName"

        val blurCommand = arrayOf(
            "-i",
            inputPath,
//            "-y",
            "-filter_complex",
            "boxblur=30",
            outPath
        )
        val rc = FFmpeg.execute(blurCommand)

        when (rc) {
            Config.RETURN_CODE_SUCCESS -> {

                Log.d(TAG, "Command execution completed successfully.")
                Log.d(TAG, "Blur Done.")
                path = outPath

                /*todo create a copy*/
                delay(1000L)
                copyFile(outPath, "/storage/emulated/0/Pictures/CopyBlur/$mVideoName")
            }
            Config.RETURN_CODE_CANCEL -> {
                Log.d(TAG, "Command execution cancelled by user.")
            }
            else -> {
                Log.d(
                    TAG,
                    String.format("Command execution failed with rc=%d and the output below.", rc)
                )
            }
        }
    }

    private suspend fun copyFile(inputPath: String?, outputPath: String?) {
        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            `in` = FileInputStream(inputPath)
            out = FileOutputStream(outputPath)
            val buffer = ByteArray(1024)
            var read: Int
            while (`in`.read(buffer).also { read = it } != -1) {
                out.write(buffer, 0, read)
            }
            `in`.close()
            `in` = null

            // write the output file (You have now copied the file)
            out.flush()
            out.close()
            out = null

            /*todo start to add watermark*/
            runWatermarkCommand(outputPath)

        } catch (fnfe1: FileNotFoundException) {
            Log.e("tag", fnfe1.message!!)
        } catch (e: java.lang.Exception) {
            Log.e("tag", e.message!!)
        }
    }

    private fun saveFont() {
        val assetManager = applicationContext.assets
        var fontFile: InputStream? = null
        try {
            fontFile = assetManager.open("inter_semi_bold.ttf")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        fontFilePath = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "twt_font.ttf"
        )
        try {
            val fos = FileOutputStream(fontFilePath)
            val buffer = ByteArray(1024)
            var length: Int
            while (fontFile!!.read(buffer).also { length = it } != -1) {
                fos.write(buffer, 0, length)
            }
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private suspend fun runWatermarkCommand(blurVideoPath: String?) {

        halfProcessDone = true

        val mVideoName = "Output_$timeStamp.mp4"
        val outPath = "/storage/emulated/0/Documents/$mVideoName"

        saveFont()

        val waterMarkCommand = arrayOf(
            "-i",
            blurVideoPath,
//            "-y",
            "-i",
            qrcode_imagePath,
            "-y",
            "-i",
            pipeline_imagePath,
            "-y",
            "-filter_complex",
            "[1]scale=300:300[img1];" +
                    "[2]scale=iw/1:-1[img2];" +
                    "[0][img1]overlay=(W-w)/2:(H-h)/2[bkg];" +
                    "[bkg][img2]overlay=(W-w)/2:(H-h)/2+250," +
                    "drawtext=text='Weed Tube':fontfile=" + fontFilePath!!.path + ":fontsize=30:fontcolor=black:" +
                    "x=(w-text_w)/2:y=(h-text_h)/2-200",
            outPath
        )
        val rc = FFmpeg.execute(waterMarkCommand)
        if (rc == Config.RETURN_CODE_SUCCESS) {

            Log.d(TAG, "Command execution completed successfully.")
            Log.d(TAG, "Watermark Added.")
            reset()
            delay(500L)
            showSuccessNotif(102, "Blur done", "Ready to share")

        } else if (rc == Config.RETURN_CODE_CANCEL) {
            Log.d(TAG, "Command execution cancelled by user.")
        } else {
            Log.d(
                TAG,
                String.format("Command execution failed with rc=%d and the output below.", rc)
            )
        }
    }

    private fun showSuccessNotif(notifiId: Int, title: String?, text: String?) {

        val myIntent = Intent(applicationContext, FfmpegActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification: Notification =
            NotificationCompat.Builder(applicationContext, default_notification_channel_id)
                .setSmallIcon(R.drawable.ic_baby_changing_station)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(uri)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setLights(Color.RED, 3000, 3000)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(notifiId, notification)
    }

    private fun reset() {
        videoTotalLength = 0
        lastPer = 0
        calculatePercentage = 0
        halfProcessDone = false
    }
}