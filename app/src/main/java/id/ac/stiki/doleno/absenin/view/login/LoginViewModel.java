package id.ac.stiki.doleno.absenin.view.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.domain.DoLogin;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final DoLogin doLogin;
    private final MutableLiveData<LoginState> _loginState = new MutableLiveData<>();
    LiveData<LoginState> loginState = _loginState;

    @Inject
    public LoginViewModel(DoLogin doLogin) {
        this.doLogin = doLogin;
    }

    public void login(String email, String password) {
        doLogin.execute(email, password)
                .addOnSuccessListener(result -> _loginState.postValue(LoginState.SUCCESS))
                .addOnFailureListener(failure -> {
                    if (failure instanceof FirebaseAuthInvalidCredentialsException) {
                        _loginState.postValue(LoginState.INVALID_CREDENTIAL);
                    } else {
                        _loginState.postValue(LoginState.FAILED);
                    }
                });
    }
}
