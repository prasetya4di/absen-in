package id.ac.stiki.doleno.absenin.view.participant.ui.absent

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.domain.DoAbsent
import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class AbsentViewModel @Inject constructor(
    private val doAbsent: DoAbsent,
    private val getCurrentLocation: GetCurrentLocation
) :
    ViewModel() {
    private val _resultStatus = MutableLiveData<QRCodeResultStatus>()
    var resultStatus: LiveData<QRCodeResultStatus> = _resultStatus

    fun doAbsent(result: String) {
        getCurrentLocation.execute()
            .addOnSuccessListener { location: Location ->
                val currentLoc =
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                doAbsent(result, currentLoc)
            }
            .addOnFailureListener { e: Exception ->
                e.printStackTrace()
                _resultStatus.postValue(QRCodeResultStatus.INVALID_LOCATION)
            }
    }

    private fun doAbsent(result: String, currentLoc: LatLng) {
        Executors.newSingleThreadExecutor().execute {
            val resultStatus = doAbsent.execute(result, currentLoc)
            _resultStatus.postValue(resultStatus)
        }
    }
}