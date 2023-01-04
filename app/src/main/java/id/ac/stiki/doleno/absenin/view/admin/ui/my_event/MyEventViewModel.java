package id.ac.stiki.doleno.absenin.view.admin.ui.my_event;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.FetchAllEventByEmail;
import id.ac.stiki.doleno.absenin.domain.GetAllEvent;
import id.ac.stiki.doleno.absenin.domain.GetUser;

@HiltViewModel
public class MyEventViewModel extends ViewModel {
    private final GetAllEvent getAllEvent;
    private final GetUser getUser;
    private final FetchAllEventByEmail fetchAllEvent;
    private final MutableLiveData<MyEventState> _myEventState = new MutableLiveData<>();
    public LiveData<MyEventState> myEventState = _myEventState;
    public LiveData<List<Event>> listMyEvent;

    @Inject
    public MyEventViewModel(GetAllEvent getAllEvent, GetUser getUser, FetchAllEventByEmail fetchAllEvent) {
        this.getAllEvent = getAllEvent;
        this.listMyEvent = getAllEvent.execute();
        this.getUser = getUser;
        this.fetchAllEvent = fetchAllEvent;
    }

    public void fetchMyEvent() {
        _myEventState.postValue(MyEventState.LOADING);
        AsyncTask.execute(() -> {
            User user = getUser.execute();
            fetchAllEvent.execute(user.email)
                    .addOnSuccessListener(success -> _myEventState.postValue(MyEventState.SUCCESS))
                    .addOnFailureListener(failure -> _myEventState.postValue(MyEventState.FAILED));
        });
    }

    public Boolean isListEventEmpty() {
        return listMyEvent.getValue().isEmpty();
    }
}
