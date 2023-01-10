package id.ac.stiki.doleno.absenin.view.participant.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.GetAbsentByStatus
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getAbsentByStatus: GetAbsentByStatus) :
    ViewModel() {
    var listAbsent: List<Absent> = emptyList()

    fun getAbsent() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                listAbsent = getAbsentByStatus.execute(
                    listOf(AbsentStatus.ATTENDED.text, AbsentStatus.DID_NOT_ATTEND.text)
                )
            }
        }
    }
}