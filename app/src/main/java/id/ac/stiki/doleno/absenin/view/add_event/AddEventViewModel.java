package id.ac.stiki.doleno.absenin.view.add_event;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.AddEvent;
import id.ac.stiki.doleno.absenin.view.admin.ui.my_event.MyEventState;

@HiltViewModel
public class AddEventViewModel extends ViewModel {
    private final AddEvent addEvent;
    private final MutableLiveData<MyEventState> _myEventState = new MutableLiveData<>();
    public LiveData<MyEventState> myEventState = _myEventState;

    @Inject
    public AddEventViewModel(AddEvent addEvent) {
        this.addEvent = addEvent;
    }

    public void addEvent(Event event) {
        _myEventState.postValue(MyEventState.LOADING);
        AsyncTask.execute(() -> {
            addEvent.execute(event)
                    .addOnSuccessListener(success -> _myEventState.postValue(MyEventState.SUCCESS))
                    .addOnFailureListener(failure -> _myEventState.postValue(MyEventState.FAILED));
        });
    }
}
