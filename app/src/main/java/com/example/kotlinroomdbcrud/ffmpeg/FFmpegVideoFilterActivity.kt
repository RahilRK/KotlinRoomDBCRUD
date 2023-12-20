package com.example.kotlinroomdbcrud.ffmpeg

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.ExecuteCallback
import com.arthenica.mobileffmpeg.FFmpeg
import com.example.kotlinroomdbcrud.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FFmpegVideoFilterActivity : AppCompatActivity() {

    var TAG = "FfmpegActivity"

    lateinit var doGrayScale: Button

    val timeStamp = SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss").format(Date())

    // on below line we are creating a variable
    // for our exo player view .
    lateinit var exoPlayerView: SimpleExoPlayerView

    // on below line we are creating a
    // variable for exo player
    lateinit var exoPlayer: SimpleExoPlayer
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ffmpeg_video_filter)

        // on below line we are initializing our all variables.
        exoPlayerView = findViewById(R.id.exoPlayer)
        doGrayScale = findViewById(R.id.doGrayScale)
        doGrayScale.setOnClickListener {

            showprogress("Hold on", "Please wait...")
            runDoGrayScaleCommand()
        }
    }

    private fun runDoGrayScaleCommand() {
        val mVideoName = "Output_$timeStamp.mp4"
        val inputPath = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        val outPath = "/storage/emulated/0/Documents/$mVideoName"

        val blurCommand = arrayOf(
            "-i",
            inputPath,
            "-vf",
            "format=gray",
            "-preset", "ultrafast",
            outPath
        )
        val rcId = FFmpeg.executeAsync(blurCommand, object :ExecuteCallback{
            override fun apply(executionId: Long, rc: Int) {

                if (rc == Config.RETURN_CODE_SUCCESS) {
                    Log.d(TAG, "Command execution completed successfully.")
                    Toast.makeText(this@FFmpegVideoFilterActivity, "Done", Toast.LENGTH_SHORT).show()
                    hideprogress()
//                    playVideo(outPath)
                } else if (rc == Config.RETURN_CODE_CANCEL) {
                    Log.d(TAG, "Command execution cancelled by user.")
                } else {
                    hideprogress()
                    Log.d(
                        TAG,
                        String.format("Command execution failed with rc=%d and the output below.", rc)
                    )
                    Toast.makeText(this@FFmpegVideoFilterActivity, "Error occured", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun playVideo(outPath: String) {

        try {
            Log.d(TAG, "playVideo: outPath: $outPath")
            // bandwidthmeter is used for
            // getting default bandwidth
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

            // track selector is used to navigate between
            // video using a default seekbar.
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

            // we are parsing a video url
            // and parsing its video uri.
//            val videoURI: Uri = Uri.parse(outPath)
            val videoURI: Uri = Uri.fromFile(File(outPath))

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            /*val dataSourceFactory: DefaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory("Exoplayer_video")*/

            val dataSourceFactory: DefaultDataSourceFactory =
                DefaultDataSourceFactory(this,"ua")

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            val mediaSourse: MediaSource =
                ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)

            // inside our exoplayer view
            // we are setting our player
            exoPlayerView.player = exoPlayer

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSourse)

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.playWhenReady = true


        } catch (e: Exception) {
            // on below line we
            // are handling exception
            e.printStackTrace()
        }

    }

    private fun showprogress(title: String?, message: String?) {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(title)
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    private fun hideprogress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss()
        }
    }
}