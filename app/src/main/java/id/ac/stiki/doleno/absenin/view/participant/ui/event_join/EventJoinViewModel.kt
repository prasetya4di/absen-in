package id.ac.stiki.doleno.absenin.view.participant.ui.event_join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.User
import id.ac.stiki.doleno.absenin.domain.CheckRegisteredStatus
import id.ac.stiki.doleno.absenin.domain.GetUser
import id.ac.stiki.doleno.absenin.domain.RegisterEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventJoinViewModel @Inject constructor(
    private val getUser: GetUser,
    private val registerEvent: RegisterEvent,
    private val checkRegisteredStatus: CheckRegisteredStatus
) : ViewModel() {
    private val _eventJoinState = MutableLiveData<EventJoinState>()
    private val _isRegistered = MutableLiveData<Boolean>()
    val eventJoinState: LiveData<EventJoinState> = _eventJoinState
    val isRegistered: LiveData<Boolean> = _isRegistered

    fun joinEvent(event: Event) {
        _eventJoinState.postValue(EventJoinState.LOADING)
        viewModelScope.launch {
            val user: User = withContext(Dispatchers.Default) { getUser.execute() }
            coroutineScope {
                checkRegisteredStatus.execute(event, user.email).collectLatest {
                    if (it) {
                        _isRegistered.postValue(true)
                        _eventJoinState.postValue(EventJoinState.REGISTERED)
                    } else {
                        registerEvent.execute(event, user)
                            .addOnSuccessListener { _eventJoinState.postValue(EventJoinState.SUCCESS) }
                            .addOnFailureListener { _eventJoinState.postValue(EventJoinState.FAILED) }
                    }
                }
            }
        }
    }

    fun checkRegisterStatus(event: Event) {
        viewModelScope.launch {
            coroutineScope {
                val user = withContext(Dispatchers.Default) {
                    getUser.execute()
                }
                checkRegisteredStatus.execute(event, user?.email ?: "").collectLatest {
                    _isRegistered.postValue(it)
                }
            }
        }
    }
}