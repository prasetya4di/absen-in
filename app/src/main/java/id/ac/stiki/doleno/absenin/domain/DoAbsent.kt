package id.ac.stiki.doleno.absenin.domain

import com.google.android.gms.maps.model.LatLng
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus
import kotlinx.coroutines.flow.Flow

interface DoAbsent {
    fun execute(qrResult: String, currentLoc: LatLng): Flow<QRCodeResultStatus>
}