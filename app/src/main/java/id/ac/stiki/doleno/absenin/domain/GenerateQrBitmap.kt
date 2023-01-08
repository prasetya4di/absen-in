package id.ac.stiki.doleno.absenin.domain

import android.graphics.Bitmap
import id.ac.stiki.doleno.absenin.data.entity.Event

interface GenerateQrBitmap {
    fun execute(event: Event): Bitmap
}