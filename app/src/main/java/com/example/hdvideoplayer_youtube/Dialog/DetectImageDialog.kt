import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.example.hashtagapi.Utils.getFileSize
import com.example.hashtagapi.Utils.storeImage
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.onBackground
import com.example.hdvideoplayer_youtube.ModelData.AhaModel
import com.example.hdvideoplayer_youtube.ModelData.WebModel
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.Utils.tos
import com.example.hdvideoplayer_youtube.databinding.VideoItemBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File

@SuppressLint("SetTextI18n")
class DetectImageDialog(var activity: Activity, res: AhaModel) {
    var dialog: BottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)
    var binding: VideoItemBinding = VideoItemBinding.inflate(LayoutInflater.from(activity))

    init {
        dialog.setContentView(binding.root)
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            .setBackgroundResource(
                android.R.color.transparent
            )

        val imageUrl = res.dataList!![0].mediaUrlList!![0]


        binding.apply {
            Glide.with(activity).asBitmap().load(imageUrl).diskCacheStrategy(DiskCacheStrategy.DATA).into(object : CustomTarget<Bitmap?>() {
                    @SuppressLint("SetTextI18n")
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                    ) {
                        val width: Int = resource.width
                        val height: Int = resource.height
//                        size.text = "$width x $height"
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

            Glide.with(activity).downloadOnly().load(imageUrl).diskCacheStrategy(DiskCacheStrategy.DATA).listener(object : RequestListener<File> {
                    override fun onLoadFailed(
                        @Nullable e: GlideException?,
                        model: Any,
                        target: Target<File>,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Handle failure
                        return false
                    }

                    override fun onResourceReady(
                        resource: File,
                        model: Any,
                        target: Target<File>,
                        dataSource: com.bumptech.glide.load.DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Get file size here
                        val fileSize = resource.length()
                        imageSize.text = getFileSize(fileSize)
                        return false
                    }
                }).submit()

            imageName.text = "${System.currentTimeMillis() / 1000}.png"
            image.load(imageUrl)

            "ImageUrl : $imageUrl".log()

            btnDownload.setOnClickListener {
                Glide.with(activity).asBitmap().load(imageUrl)
                    .into(object : CustomTarget<Bitmap?>() {
                        @SuppressLint("SetTextI18n")
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                        ) {
                            "Resource: $resource".log()
                            onBackground {
                                storeImage(resource, "")
                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
                ("File Save Successfully").tos(activity)
                dialog.dismiss()
            }

            btnPlay.setOnClickListener {
                dialog.dismiss()
            }
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}