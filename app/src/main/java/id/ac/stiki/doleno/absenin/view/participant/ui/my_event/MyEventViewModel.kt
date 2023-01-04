package id.ac.stiki.doleno.absenin.view.participant.ui.my_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.FetchAllAbsentHistory
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MyEventViewModel @Inject constructor(
    private val fetchAllAbsent: FetchAllAbsentHistory,
    getAllAbsentHistory: GetAllAbsentHistory
) : ViewModel() {
    private val _myEventState: MutableLiveData<MyEventState> = MutableLiveData()
    val myEventState: LiveData<MyEventState> = _myEventState
    val absents: LiveData<List<Absent>> = getAllAbsentHistory.execute()

    fun fetchAllAbsent() {
        _myEventState.postValue(MyEventState.LOADING)

        Executors.newSingleThreadExecutor().execute {
            fetchAllAbsent.execute()
                .addOnSuccessListener {
                    _myEventState.postValue(MyEventState.SUCCESS)
                }
                .addOnFailureListener {
                    _myEventState.postValue(MyEventState.FAILED)
                }
        }
    }

    fun isAbsentsEmpty(): Boolean = absents.value?.isEmpty() ?: true
}