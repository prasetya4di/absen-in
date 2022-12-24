package id.ac.stiki.doleno.absenin.view.admin.ui.my_profile;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.DoLogout;
import id.ac.stiki.doleno.absenin.domain.GetUser;

@HiltViewModel
public class MyProfileViewModel extends ViewModel {
    private final GetUser getUser;
    private final DoLogout doLogout;
    private final MutableLiveData<MyProfileState> _myProfileState = new MutableLiveData<>();
    public User user = null;
    public LiveData<MyProfileState> myProfileState = _myProfileState;

    @Inject
    public MyProfileViewModel(GetUser getUser, DoLogout doLogout) {
        this.getUser = getUser;
        this.doLogout = doLogout;
    }

    public void getUser() {
        _myProfileState.postValue(MyProfileState.LOADING);
        AsyncTask.execute(() -> {
            user = getUser.execute();
            _myProfileState.postValue(MyProfileState.SUCCESS);
        });
    }

    public void logout() {
        AsyncTask.execute(doLogout::execute);
    }
}
