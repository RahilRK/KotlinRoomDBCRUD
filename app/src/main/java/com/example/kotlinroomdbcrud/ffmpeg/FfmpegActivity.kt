package com.example.kotlinroomdbcrud.ffmpeg

import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.FFmpeg
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.ffmpeg.worker.DoBlurWorker
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

//import static com.arthenica.mobileffmpeg.FFmpeg.RETURN_CODE_CANCEL;
//import static com.arthenica.mobileffmpeg.FFmpeg.RETURN_CODE_SUCCESS;
//import com.arthenica.mobileffmpeg.FFmpegExecution;
class FfmpegActivity : AppCompatActivity() {

    var TAG = "FfmpegActivity"

    lateinit var doBlur_btn: Button
    lateinit var doBlurInBGBtn_btn: Button

    var path = ""
    var fontFilePath: File? = null

    val timeStamp = SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ffmpeg_activity)

        doBlur_btn = findViewById(R.id.doBlurBtn)
        doBlurInBGBtn_btn = findViewById(R.id.doBlurInBGBtn)
        doBlur_btn.setOnClickListener(View.OnClickListener {
            runBlurCommand()
//            runWatermarkCommand("");
        })

        doBlurInBGBtn_btn.setOnClickListener(View.OnClickListener {

            val workManager: WorkManager = WorkManager.getInstance(applicationContext)
            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val doWorkForBlurRequest = OneTimeWorkRequest.Builder(DoBlurWorker::class.java)
                .setConstraints(constraints)
                .build()
            workManager.enqueue(doWorkForBlurRequest)
            workManager.getWorkInfoByIdLiveData(doWorkForBlurRequest.id)
                .observe(this, androidx.lifecycle.Observer {
                    Log.d(TAG, "doWorkForBlur state: ${it.state.name}")

                    if(it.state.name == "SUCCEEDED") {
                        workManager.cancelWorkById(doWorkForBlurRequest.id)
                    }
                })
        })
    }

    private fun runBlurCommand() {
        val mVideoName = "Output_" + timeStamp + ".mp4"
        val inputPath =
            "/storage/emulated/0/Download/4_4615941674562787346_Video_5d2be832f91bac0f29771454a9145d3c.mp4"
        val imagePath = "/storage/emulated/0/Download/overlay_img.jpg"
        val outPath = "/storage/emulated/0/Documents/$mVideoName"

        /*String[] command = {"-i",
                inputPath,
//                "-vf",
//                "boxblur=30",
                "-i",
                imagePath,
                "-filter_complex",
                "overlay=5:5",
                "-codec:a",
                "copy",
                outPath};*/

        /*String[] command = {"-i",
                inputPath,
//                "-vf",
//                "boxblur=30",
                "-i",
                imagePath,
                "-filter_complex",
                "[1]scale=100:100[w];[0][w]overlay=50:100-h-5[v]",
                "-map",
                "[v]",
//                "-codec:a",
                "-c:a",
                "copy",
                outPath};*/

        /*String[] command = {
                "-i",
                inputPath,
                "-vf",
                "boxblur=30",

                "-i",
                imagePath,
                "-filter_complex",
                "[1]scale=254:254[w];[0][w]" + " overlay=(main_w-overlay_w)/2:(main_h-overlay_h)/2",
                "-codec:a",
                "copy",
                outPath
        };*/

        /*String[] blurCommand = {
                "-i",
                inputPath,
//                "-y",
                "-vf",
//                "boxblur=30",
                "drawtext=text='Rahil':fontfile="+fontFilePath.getPath()+":fontsize=30:fontcolor=white:x=10:y=10",
                outPath
        };*/

        val blurCommand = arrayOf(
            "-i",
            inputPath,  //                "-y",
            "-filter_complex",
            "boxblur=30",
            "-preset", "ultrafast",
            outPath
        )
        val rc = FFmpeg.execute(blurCommand)
        if (rc == Config.RETURN_CODE_SUCCESS) {
            Log.d(TAG, "Command execution completed successfully.")
            Toast.makeText(this, "Blur Done", Toast.LENGTH_SHORT).show()
            path = outPath
            Handler().postDelayed({
                val file = File(outPath)
                if (file.exists()) {

                    /*todo create a copy*/
                    copyFile(outPath, "/storage/emulated/0/Pictures/CopyBlur/$mVideoName")
                } else {
                    Log.d(TAG, "File not exist.")
                }
            }, 1000)
        } else if (rc == Config.RETURN_CODE_CANCEL) {
            Log.d(TAG, "Command execution cancelled by user.")
        } else {
            Log.d(
                TAG,
                String.format("Command execution failed with rc=%d and the output below.", rc)
            )
            Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyFile(inputPath: String?, outputPath: String?) {
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

            /*todo start to add watermark*/runWatermarkCommand(outputPath)
        } catch (fnfe1: FileNotFoundException) {
            Log.e("tag", fnfe1.message!!)
        } catch (e: Exception) {
            Log.e("tag", e.message!!)
        }
    }

    private fun saveFont() {
        val assetManager = assets
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

    private fun runWatermarkCommand(blurVideoPath: String?) {
        val mVideoName = "Output_" + timeStamp + ".mp4"
        val qrcode_imagePath = "/storage/emulated/0/Download/overlay_img.jpg"
        val pipeline_imagePath = "/storage/emulated/0/Download/Pipeline.png"
        val inputPath =
            "/storage/emulated/0/Download/4_4615941674562787346_Video_5d2be832f91bac0f29771454a9145d3c.mp4"
        val outPath = "/storage/emulated/0/Documents/$mVideoName"

        /*String[] waterMarkCommand = {
                "-i",
                blurVideoPath,
//                "-y",
                "-i",
                qrcode_imagePath,
                "-y",
                "-filter_complex",
                "[1]scale=254:254[w];[0][w]" + " overlay=(main_w-overlay_w)/2:(main_h-overlay_h)/2",
                "-codec:a",
                "copy",
                outPath
        };*/

        saveFont()
        val waterMarkCommand = arrayOf(
            "-i",
            blurVideoPath,  //                "-y",
            "-i",
            qrcode_imagePath,
            "-y",
            "-i",
            pipeline_imagePath,
            "-y",
            "-filter_complex",  //                "[1]scale=254:254[result];" + "[0][result]overlay=(main_w-overlay_w)/2:(main_h-overlay_h)/2",
            //                "-codec:a",
            //                "copy",
            //                "[0][1]overlay=50:100[result];[result][2]overlay=30:60",
            "[1]scale=300:300[img1];" +
                    "[2]scale=iw/1:-1[img2];" +
                    "[0][img1]overlay=(W-w)/2:(H-h)/2[bkg];" +
                    "[bkg][img2]overlay=(W-w)/2:(H-h)/2+200," +
                    "drawtext=text='Weed Tube':fontfile=" + fontFilePath!!.path + ":fontsize=30:fontcolor=black:" +
                    "x=(w-text_w)/2:y=(h-text_h)/2-200",
            "-preset", "ultrafast",
            outPath
        )
        val rc = FFmpeg.execute(waterMarkCommand)
        if (rc == Config.RETURN_CODE_SUCCESS) {
            Log.d(TAG, "Command execution completed successfully.")
            Toast.makeText(this, "Watermark Added", Toast.LENGTH_SHORT).show()
        } else if (rc == Config.RETURN_CODE_CANCEL) {
            Log.d(TAG, "Command execution cancelled by user.")
        } else {
            Log.d(
                TAG,
                String.format("Command execution failed with rc=%d and the output below.", rc)
            )
            Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show()
        }
    }

/*
    companion object {
        val timeStamp = SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(Date())
    }
*/
}