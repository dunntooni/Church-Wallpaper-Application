// Most code here came from user PerracoLabs in response to a question in this stackoverflow thread:
// https://stackoverflow.com/questions/56904485/how-to-save-an-image-in-android-q-using-mediastore

package com.example.churchwallpaperapplication

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.content.ContextWrapper
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

object saveImageHelper {
    fun saveImageToInternalStorage(
        mContext: Context,
        bitmap: Bitmap
    ): Uri {
        val mTimeStamp =
            SimpleDateFormat("ddMMyyyy_HHmm").format(Date())
        val mImageName = "snap_$mTimeStamp.jpg"

        val resolver = mContext.contentResolver

        val newImageDetails = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, mImageName)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        }

        var uri: Uri? = null

        try {
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, newImageDetails)
                ?: throw IOException("Couldn't create new MediaStore record.")

            resolver.openOutputStream(uri)?.use {
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, it))
                    throw IOException("Couldn't save bitmap.")
            } ?: throw IOException("Couldn't open output stream")

            return uri
        } catch (e: IOException) {
            uri?.let {orphanUri ->
                resolver.delete(orphanUri, null, null)
            }

            throw e
        }








//        val imageCollection =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//            } else {
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            }



//        val wrapper = ContextWrapper(mContext)
//        var directory = wrapper.getDir("Downloads", Context.MODE_PRIVATE)
//        var file = File(directory, "snap_$mImageName.jpg")
//        try {
//            var stream = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//            stream.flush()
//            stream.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return Uri.parse(file.absolutePath)
        return uri
    }
}