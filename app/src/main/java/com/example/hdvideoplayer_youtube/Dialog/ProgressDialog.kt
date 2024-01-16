package com.example.hdvideoplayer_youtube.Dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Environment
import android.view.WindowManager
import com.example.hashtagapi.Utils.YTAPI
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.onBackground
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ProgressItemBinding
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("SetTextI18n")
class
ProgressDialog(
    var activity: Activity,
    var id: String,
    var videoUrl: String,
    val name : String,
    onClick: (String) -> Unit
) {

    var bind = ProgressItemBinding.inflate(activity.layoutInflater)
    var dialog = Dialog(activity)

    init {
        dialog.setContentView(bind.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()

        ("Video: $videoUrl").log()

        onBackground {
            try {

                // create a URL object for the video
                val url = URL(videoUrl)

                // create a connection to the server
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

                // set the request method to GET
                connection.requestMethod = "GET"

                // connect to the server
                connection.connect()

                // get the content length of the video
                val contentLength: Int = connection.contentLength

                // create a stream to read the data from the server
                val input: InputStream = connection.inputStream

                // create a stream to write the data to a file

                "Id = $id".log()
                val filePath: String =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/$YTAPI/$name${id}.mp4"

                val output = FileOutputStream(File(filePath))

                "File Path = ${File(filePath)}".log()

                // set the buffer size
                val buffer = ByteArray(1024)
                var bytesRead: Int

                // download the video and update the progress bar

                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                    val progresss = ((output.channel.size() * 100 / contentLength).toInt())
                    ("Progress = $progresss").log()

//                    if (progresss == 98)
//                        break
                    activity.runOnUiThread {
                        bind.progressBar.progress = progresss
                        bind.tvnumber.text = "$progresss%"
                    }
                }

                ("Now").log()
                activity.runOnUiThread {
                    bind.progressBar.progress = 100
                    bind.tvnumber.text = "100%"
                    onClick.invoke(filePath)
                    dismiss()
                }
                // close the input and output streams
                output.close()
                input.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun dismiss() = dialog.dismiss()
}