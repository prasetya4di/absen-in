package id.ac.stiki.doleno.absenin.view.participant.ui.all_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.FetchAllEvent
import id.ac.stiki.doleno.absenin.domain.GetAllEvent
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class AllEventViewModel @Inject constructor(
    private val getAllEvent: GetAllEvent,
    private val fetchAllEvent: FetchAllEvent
) : ViewModel() {
    private val _allEventState: MutableLiveData<AllEventState> = MutableLiveData()
    val myEventState: LiveData<AllEventState> = _allEventState
    var events: List<Event> = emptyList()

    fun fetchMyEvent() {
        _allEventState.postValue(AllEventState.LOADING)
        Executors.newSingleThreadExecutor().execute {
            fetchAllEvent.execute()
                .addOnSuccessListener {
                    Executors.newSingleThreadExecutor().execute {
                        events = getAllEvent.execute()
                        _allEventState.postValue(AllEventState.SUCCESS)
                    }
                }
                .addOnFailureListener {
                    _allEventState.postValue(AllEventState.FAILED)
                }
        }
    }

    fun isListEventEmpty(): Boolean {
        return events.isEmpty()
    }
}