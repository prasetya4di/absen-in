package id.ac.stiki.doleno.absenin.view.participant.ui.all_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot
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
    val events: LiveData<List<Event>> = getAllEvent.execute()

    fun fetchMyEvent() {
        _allEventState.postValue(AllEventState.LOADING)
        Executors.newSingleThreadExecutor().execute {
            fetchAllEvent.execute()
                .addOnSuccessListener { success: QuerySnapshot? ->
                    _allEventState.postValue(
                        AllEventState.SUCCESS
                    )
                }
                .addOnFailureListener { failure: Exception? ->
                    _allEventState.postValue(
                        AllEventState.FAILED
                    )
                }
        }
    }

    fun isListEventEmpty(): Boolean {
        return events.value?.isEmpty() ?: true
    }
}