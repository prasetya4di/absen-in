package id.ac.stiki.doleno.absenin.view.splash;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.domain.CheckLoggedInStatus;
import id.ac.stiki.doleno.absenin.domain.GetUserRole;
import id.ac.stiki.doleno.absenin.util.enums.Role;

@HiltViewModel
public class SplashViewModel extends ViewModel {

    private final CheckLoggedInStatus checkLoggedInStatus;
    private final GetUserRole getUserRole;
    private final MutableLiveData<SplashState> _splashState = new MutableLiveData<>();
    LiveData<SplashState> splashState = _splashState;
    private Role userRole = Role.PARTICIPANT;

    @Inject
    public SplashViewModel(CheckLoggedInStatus checkLoggedInStatus, GetUserRole getUserRole) {
        this.checkLoggedInStatus = checkLoggedInStatus;
        this.getUserRole = getUserRole;
    }

    void checkLoggedInStatus() {
        _splashState.postValue(SplashState.LOADING);
        AsyncTask.execute(() -> {
            if (checkLoggedInStatus.execute()) {
                userRole = getUserRole.execute();
                _splashState.postValue(SplashState.LOGGED_IN);
            } else {
                _splashState.postValue(SplashState.NOT_LOGGED_IN);
            }
        });
    }

    public Role getUserRole() {
        return userRole;
    }
}
