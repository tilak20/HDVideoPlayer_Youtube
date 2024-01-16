package com.example.hdvideoplayer_youtube.Activity

import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.folderImageList
import com.example.hdvideoplayer_youtube.Adapter.FolderImageAdapter
import com.example.hdvideoplayer_youtube.ModelData.CurrentImageMd
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityFolderImagesBinding
import com.google.ads.sdk.AdsManager
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import java.io.File


class FolderImagesAct : BaseAct<ActivityFolderImagesBinding>() {

    lateinit var videoUri: Uri
    var DeletePosition: Int = 0

    lateinit var folderVideoAdapter: FolderImageAdapter

    companion object {
        var currentImageList = ArrayList<CurrentImageMd>()
    }

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityFolderImagesBinding.inflate(layoutInflater)

    override fun initUI() {

        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        val position = intent.getIntExtra("position", 0)
        val folder_name = intent.getStringExtra("folderName")

//        "image position == ${position}".log()
//        "folderList == ${folderImageList.size}".log()

        Application.putString("playlistFOlderName", folder_name.toString())

        currentImageList = getAllPlayableImages(folderImageList[position].videoBucketId)

        setRV(currentImageList)

        binding.materialTextView2.text = folder_name

        binding.shapeableImageView2.setOnClickListener {
            AdsManager.getInstance().showOnbackPressAdExtra(this@FolderImagesAct) { finish() }
        }
    }

    fun setRV(folderList: ArrayList<CurrentImageMd>) {
        folderVideoAdapter =
            FolderImageAdapter(this, folderList, onClick = { title, uri, position ->
                AdsManager.getInstance().showInterstitialAd(this@FolderImagesAct) {
                    startActivity(
                        Intent(this@FolderImagesAct, ImageShowAct::class.java).putExtra(
                            "position", position
                        ).putExtra("image_title", title)
                    )
                }
            }, onDeleteClick = { it, pos ->
                videoUri = it.toUri()
                DeletePosition = pos

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    contentResolver.delete(videoUri, null, null)
                    currentImageList.removeAt(DeletePosition)
                    folderVideoAdapter.notifyItemRemoved(DeletePosition)
                } else {
                    try {
                        deleteImageAPI29(videoUri)
                    } catch (e: Exception) {
                        "error = ${e.message}".log()
                    }
                }
            })
//        val scaleInAnimationAdapter = ScaleInAnimationAdapter(folderVideoAdapter)
//        scaleInAnimationAdapter.setDuration(300)
//        scaleInAnimationAdapter.setInterpolator(OvershootInterpolator())
//        scaleInAnimationAdapter.setFirstOnly(false)
        binding.rcView.adapter = folderVideoAdapter
    }


    fun getAllPlayableImages(videoBucketId: String): ArrayList<CurrentImageMd> {
        currentImageList.clear()
        val selection = MediaStore.Images.Media.BUCKET_ID + " like? "

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DESCRIPTION,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID
        )
        val sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC"

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            arrayOf(videoBucketId),
            sortOrder
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val dateAddesColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val mimeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
            val descColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DESCRIPTION)
            val dateTakenColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val bucketNameColumn =
                it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)

            while (it.moveToNext()) {
                val imageId = it.getLong(idColumn)
                val imageName = it.getString(nameColumn)
                val imagePath = it.getString(dataColumn)
                val imageSize = it.getString(sizeColumn).toLong()
                val imageDateadded = it.getInt(dateAddesColumn)
                val imageMime = it.getString(mimeColumn)
                val imageTitle = it.getString(titleColumn)
                val imageDescription =
                    if (it.getString(descColumn) == null) "null" else it.getString(descColumn)
                val imageDatetaken =
                    if (it.getString(dateTakenColumn) == null) "null" else it.getString(
                        dateTakenColumn
                    )

                val imageBucket = it.getString(bucketNameColumn)
                val imageBucketId = it.getString(bucketIdColumn)
                val imageUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId
                ).toString()
                val thumbnail = Uri.fromFile(File(imagePath))

                currentImageList.add(
                    CurrentImageMd(
                        imageId,
                        imageName,
                        imagePath,
                        imageSize,
                        imageDateadded,
                        imageMime,
                        imageTitle,
                        imageDatetaken,
                        imageBucket,
                        imageUri,
                        thumbnail
                    )
                )
            }
        }
        return currentImageList
    }

    fun deleteImageAPI29(uri: Uri?) {
        val resolver = contentResolver
        try {
            resolver.delete(uri!!, null, null)
        } catch (securityException: SecurityException) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val recoverableSecurityException = securityException as RecoverableSecurityException
                val senderRequest = IntentSenderRequest.Builder(
                    recoverableSecurityException.userAction.actionIntent.intentSender
                ).build()
                deleteResultLauncher.launch(senderRequest)
            }
        }
    }

    var deleteResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result != null) {
                if (result.resultCode == RESULT_OK) {
                    val deleted = deleteMediaFile(videoUri, contentResolver)
                    if (deleted) {
                        ("Image deleted.").tos()

                        currentImageList.removeAt(DeletePosition)
                        folderVideoAdapter.notifyItemRemoved(DeletePosition)

                        if (currentImageList.size == 0) {
                            binding.txtNotFound.visibility = View.VISIBLE
                            binding.rcView.visibility = View.GONE
                            "Empty".log()
                        } else {
                            "not Empty".log()
                            binding.txtNotFound.visibility = View.GONE
                            binding.rcView.visibility = View.VISIBLE
                        }
                    } else {
                        ("Video Not deleted.").tos()
                    }
                }
            }
        }

    fun deleteMediaFile(contentUri: Uri, contentResolver: ContentResolver): Boolean {
        return try {
            val deletedRows = contentResolver.delete(contentUri, null, null)
            deletedRows > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}