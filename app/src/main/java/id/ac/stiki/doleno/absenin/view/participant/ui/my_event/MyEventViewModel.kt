package id.ac.stiki.doleno.absenin.view.participant.ui.my_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.FetchAllAbsentHistory
import id.ac.stiki.doleno.absenin.domain.GetAbsentByStatus
import id.ac.stiki.doleno.absenin.domain.GetUser
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MyEventViewModel @Inject constructor(
    private val getUser: GetUser,
    private val fetchAllAbsent: FetchAllAbsentHistory,
    private val getAbsentByStatus: GetAbsentByStatus
) : ViewModel() {
    private val _myEventState: MutableLiveData<MyEventState> = MutableLiveData()
    val myEventState: LiveData<MyEventState> = _myEventState
    var absents: List<Absent> = emptyList()

    fun fetchAllAbsent() {
        _myEventState.postValue(MyEventState.LOADING)

        Executors.newSingleThreadExecutor().execute {
            val user = getUser.execute()
            fetchAllAbsent.execute(user.email)
                .addOnSuccessListener {
                    Executors.newSingleThreadExecutor().execute {
                        absents = getAbsentByStatus.execute(listOf(AbsentStatus.REGISTERED.text))
                        _myEventState.postValue(MyEventState.SUCCESS)
                    }
                }
                .addOnFailureListener {
                    _myEventState.postValue(MyEventState.FAILED)
                }
        }
    }

    fun isAbsentsEmpty(): Boolean = absents.isEmpty()
}