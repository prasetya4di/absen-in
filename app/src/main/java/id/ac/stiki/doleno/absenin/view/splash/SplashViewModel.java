package id.ac.stiki.doleno.absenin.view.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.domain.CheckLoggedInStatus;

@HiltViewModel
public class SplashViewModel extends ViewModel {

    private final CheckLoggedInStatus checkLoggedInStatus;
    private final MutableLiveData<SplashState> _splashState = new MutableLiveData<>();
    LiveData<SplashState> splashState = _splashState;

    public SplashViewModel(CheckLoggedInStatus checkLoggedInStatus) {
        this.checkLoggedInStatus = checkLoggedInStatus;
    }

    void checkLoggedInStatus() {
        _splashState.postValue(SplashState.LOADING);
        if (checkLoggedInStatus.execute()) {
            _splashState.postValue(SplashState.LOGGED_IN);
        } else {
            _splashState.postValue(SplashState.NOT_LOGGED_IN);
        }
    }
}
