package id.ac.stiki.doleno.absenin.view.participant.ui.absent

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.zxing.NotFoundException
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.domain.CheckEventAvailability
import id.ac.stiki.doleno.absenin.domain.DoAbsent
import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AbsentViewModel @Inject constructor(
    private val doAbsent: DoAbsent,
    private val checkEventAvailability: CheckEventAvailability,
    private val getCurrentLocation: GetCurrentLocation
) : ViewModel() {
    private val _resultStatus = MutableLiveData<QRCodeResultStatus>()
    var resultStatus: LiveData<QRCodeResultStatus> = _resultStatus

    fun doAbsent(result: String) {
        viewModelScope.launch {
            runCatching {
                val eventAvailable = checkEventAvailability.execute(result.toLong())
                if (eventAvailable) {
                    getCurrentLocation.execute()
                        .addOnSuccessListener { location: Location ->
                            val currentLoc = LatLng(location.latitude, location.longitude)
                            doAbsent(result, currentLoc)
                        }
                        .addOnFailureListener { e: Exception ->
                            e.printStackTrace()
                            _resultStatus.postValue(QRCodeResultStatus.INVALID_LOCATION)
                        }
                } else throw NotFoundException.getNotFoundInstance()
            }.onFailure {
                _resultStatus.postValue(QRCodeResultStatus.INVALID)
            }
        }
    }

    private fun doAbsent(result: String, currentLoc: LatLng) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                doAbsent.execute(result, currentLoc).collectLatest {
                    _resultStatus.postValue(it)
                }
            }
        }
    }
}