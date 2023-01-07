package id.ac.stiki.doleno.absenin.view.add_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.domain.AddEvent
import id.ac.stiki.doleno.absenin.domain.GetUser
import id.ac.stiki.doleno.absenin.util.model.SelectedLocationModel
import id.ac.stiki.doleno.absenin.view.admin.ui.my_event.MyEventState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEvent: AddEvent,
    private val getUser: GetUser
) : ViewModel() {
    private val _myEventState = MutableLiveData<MyEventState>()

    @JvmField
    var myEventState: LiveData<MyEventState> = _myEventState
    fun addEvent(
        title: String,
        description: String,
        date: Date,
        selectedLocationModel: SelectedLocationModel
    ) {
        _myEventState.postValue(MyEventState.LOADING)
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val user = getUser.execute()
                val event = Event(
                    title,
                    description,
                    user.name,
                    user.email,
                    date,
                    selectedLocationModel.location,
                    selectedLocationModel.locationName
                )
                addEvent.execute(event)
                    .addOnSuccessListener { _myEventState.postValue(MyEventState.SUCCESS) }
                    .addOnFailureListener { _myEventState.postValue(MyEventState.FAILED) }
            }
        }
    }
}