package id.ac.stiki.doleno.absenin.view.admin.ui.my_event_detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.domain.DownloadQrCode
import id.ac.stiki.doleno.absenin.domain.FetchAllEventParticipant
import id.ac.stiki.doleno.absenin.domain.GenerateQrBitmap
import id.ac.stiki.doleno.absenin.domain.GetEventParticipant
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MyEventDetailViewModel @Inject constructor(
    private val getEventParticipant: GetEventParticipant,
    private val fetchAllEventParticipant: FetchAllEventParticipant,
    private val generateQrBitmap: GenerateQrBitmap,
    private val downloadQrCode: DownloadQrCode
) : ViewModel() {

    private val _myEventDetailViewState = MutableLiveData<MyEventDetailViewState>()
    val myEventDetailViewState: LiveData<MyEventDetailViewState> = _myEventDetailViewState
    var participants: List<EventParticipant> = emptyList()

    fun getEventParticipant(event: Event) {
        _myEventDetailViewState.postValue(MyEventDetailViewState.LOADING_PARTICIPANT)
        viewModelScope.launch {
            fetchAllEventParticipant.execute(event.uid)
                .addOnSuccessListener {
                    Executors.newSingleThreadExecutor().execute {
                        participants = getEventParticipant.execute(event.uid)
                        _myEventDetailViewState.postValue(MyEventDetailViewState.SUCCESS)
                    }
                }
                .addOnFailureListener { _myEventDetailViewState.postValue(MyEventDetailViewState.FAILED) }

        }
    }

    fun downloadQrCode(event: Event) {
        downloadQrCode.execute(event)
    }

    fun generateQrCode(event: Event): Bitmap = generateQrBitmap.execute(event)
}