package id.ac.stiki.doleno.absenin.domain.impl

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.GenerateQrBitmap


class GenerateQrBitmapImpl : GenerateQrBitmap {
    override fun execute(event: Event): Bitmap {
        val barcodeEncoder = BarcodeEncoder()
        return barcodeEncoder.encodeBitmap(event.uid.toString(), BarcodeFormat.QR_CODE, 400, 400)
    }
}