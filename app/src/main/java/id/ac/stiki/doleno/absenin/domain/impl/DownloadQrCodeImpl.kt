package id.ac.stiki.doleno.absenin.domain.impl

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.DownloadQrCode
import java.io.File
import javax.inject.Inject

class DownloadQrCodeImpl @Inject constructor(
    private val context: Context
) : DownloadQrCode {
    override fun execute(event: Event) {
        try {
            val fileName = "${event.uid}_${event.eventTitle}.jpg"

            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/")
                values.put(MediaStore.MediaColumns.IS_PENDING, 1)
            } else {
                val directory: File =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                val file = File(directory, fileName)
                values.put(MediaStore.MediaColumns.DATA, file.absolutePath)
            }

            val uri: Uri? =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            if (uri != null) {
                context.contentResolver.openOutputStream(uri).use { output ->
                    val barcodeEncoder = BarcodeEncoder()
                    val bm: Bitmap = barcodeEncoder.encodeBitmap(
                        event.uid.toString(),
                        BarcodeFormat.QR_CODE,
                        800,
                        800
                    )
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, output)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}