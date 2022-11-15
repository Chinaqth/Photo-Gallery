package com.fk.photogallery.base.utils

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.fk.photogallery.base.model.RuntimeData
import com.fk.photogallery.core.application.PhotoGalleryApplication
import java.io.*
import java.lang.Exception

object SaveUtil {
    /**
     * 保存文件，文件名为当前日期
     */
    fun saveBitmap(bitmap: Bitmap): Boolean {
        val fileName: String
        val file: File
        val brand = Build.BRAND
        fileName = if (brand == "xiaomi") { // 小米手机brand.equals("xiaomi")
            Environment.getExternalStorageDirectory().path + "/DCIM/Camera/" + System.currentTimeMillis() + ".jpg"
        } else if (brand.equals("Huawei", ignoreCase = true)) {
            Environment.getExternalStorageDirectory().path + "/DCIM/Camera/" + System.currentTimeMillis() + ".jpg"
        } else { // Meizu 、Oppo
            Environment.getExternalStorageDirectory().path + "/DCIM/" + System.currentTimeMillis() + ".jpg"
        }
        file = if (Build.VERSION.SDK_INT >= 29) {
            saveSignImage(bitmap)
            return true
        } else {
            Log.v("saveBitmap brand", "" + brand)
            File(fileName)
        }
        if (file.exists()) {
            file.delete()
        }
        val out: FileOutputStream
        try {
            out = FileOutputStream(file)
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush()
                out.close()
                MediaStore.Images.Media.insertImage(
                    PhotoGalleryApplication().getAppInstance()?.contentResolver,
                    file.absolutePath,
                    fileName,
                    null
                )
            }
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFoundException", "FileNotFoundException:" + e.message.toString())
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            Log.e("IOException", "IOException:" + e.message.toString())
            e.printStackTrace()
            return false
        } catch (e: Exception) {
            Log.e("IOException", "IOException:" + e.message.toString())
            e.printStackTrace()
            return false
        }
        PhotoGalleryApplication().getAppInstance()?.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(
                    "file://$fileName"
                )
            )
        )
        return true
    }

    fun saveSignImage(bitmap: Bitmap) {
        try {
            //设置保存参数到ContentValues中
            val contentValues = ContentValues()

            //设置文件名
            contentValues.put(
                MediaStore.Images.Media.DISPLAY_NAME,
                System.currentTimeMillis().toString() + ".jpg"
            )
            //兼容Android Q和以下版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
                //RELATIVE_PATH是相对路径不是绝对路径
                //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
                contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/")
                //contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Music/signImage");
            } else {
                contentValues.put(
                    MediaStore.Images.Media.DATA,
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path
                )
            }
            //设置文件类型
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG")
            //执行insert操作，向系统文件夹中添加文件
            //EXTERNAL_CONTENT_URI代表外部存储器，该值不变

            val uri: Uri? = PhotoGalleryApplication().getAppInstance().contentResolver
                ?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                val outputStream: OutputStream? =
                    PhotoGalleryApplication().getAppInstance()?.contentResolver?.openOutputStream(uri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                    outputStream.flush()
                    outputStream.close()
                }
            } else{
                Log.d("QQQ","fail")

            }
        } catch (e: Exception) {
            Log.d("QQQ",e.toString())
        }
    }
}