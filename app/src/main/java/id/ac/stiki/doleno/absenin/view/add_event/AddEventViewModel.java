package id.ac.stiki.doleno.absenin.view.add_event;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.AddEvent;
import id.ac.stiki.doleno.absenin.domain.GetUser;
import id.ac.stiki.doleno.absenin.util.model.SelectedLocationModel;
import id.ac.stiki.doleno.absenin.view.admin.ui.my_event.MyEventState;

@HiltViewModel
public class AddEventViewModel extends ViewModel {
    private final AddEvent addEvent;
    private final GetUser getUser;
    private final MutableLiveData<MyEventState> _myEventState = new MutableLiveData<>();
    public LiveData<MyEventState> myEventState = _myEventState;

    @Inject
    public AddEventViewModel(AddEvent addEvent, GetUser getUser) {
        this.addEvent = addEvent;
        this.getUser = getUser;
    }

    public void addEvent(String title, String description, Date date, SelectedLocationModel selectedLocationModel) {
        _myEventState.postValue(MyEventState.LOADING);
        AsyncTask.execute(() -> {
            User user = getUser.execute();
            Event event = new Event(
                    title,
                    description,
                    user.name,
                    date,
                    selectedLocationModel.getLocation(),
                    selectedLocationModel.getLocationName()
            );
            addEvent.execute(event)
                    .addOnSuccessListener(success -> _myEventState.postValue(MyEventState.SUCCESS))
                    .addOnFailureListener(failure -> _myEventState.postValue(MyEventState.FAILED));
        });
    }
}
