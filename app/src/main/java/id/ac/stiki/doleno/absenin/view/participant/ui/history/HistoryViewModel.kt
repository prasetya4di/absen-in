package id.ac.stiki.doleno.absenin.view.participant.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.FetchAllAbsentHistory
import id.ac.stiki.doleno.absenin.domain.GetAbsentByStatus
import id.ac.stiki.doleno.absenin.domain.GetUser
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getUser: GetUser,
    private val getAbsentByStatus: GetAbsentByStatus,
    private val fetchAllAbsentHistory: FetchAllAbsentHistory
) : ViewModel() {
    private val _historyViewState = MutableLiveData<HistoryViewState>()
    val historyViewState: LiveData<HistoryViewState> = _historyViewState
    var listAbsent: List<Absent> = emptyList()

    fun getAbsent() {
        viewModelScope.launch {
            _historyViewState.postValue(HistoryViewState.LOADING)
            withContext(Dispatchers.Default) {
                val user = getUser.execute()
                fetchAllAbsentHistory.execute(user.email)
                    .addOnSuccessListener {
                        Executors.newSingleThreadExecutor().execute {
                            listAbsent = getAbsentByStatus.execute(
                                listOf(AbsentStatus.ATTENDED.text, AbsentStatus.DID_NOT_ATTEND.text)
                            )
                            _historyViewState.postValue(HistoryViewState.SUCCESS)
                        }
                    }
                    .addOnFailureListener { _historyViewState.postValue(HistoryViewState.FAILED) }
            }
        }
    }
}