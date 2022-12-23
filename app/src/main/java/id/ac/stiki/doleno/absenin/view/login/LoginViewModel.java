package id.ac.stiki.doleno.absenin.view.login;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.domain.DoLogin;
import id.ac.stiki.doleno.absenin.domain.GetUserRole;
import id.ac.stiki.doleno.absenin.util.enums.Role;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final DoLogin doLogin;
    private final GetUserRole getUserRole;
    private Role userRole = Role.PARTICIPANT;
    private final MutableLiveData<LoginState> _loginState = new MutableLiveData<>();
    LiveData<LoginState> loginState = _loginState;

    @Inject
    public LoginViewModel(DoLogin doLogin, GetUserRole getUserRole) {
        this.doLogin = doLogin;
        this.getUserRole = getUserRole;
    }

    public void login(String email, String password) {
        _loginState.postValue(LoginState.LOADING);
        doLogin.execute(email, password)
                .addOnSuccessListener(result -> AsyncTask.execute(() -> {
                    _loginState.postValue(LoginState.SUCCESS);
                    userRole = getUserRole.execute();
                }))
                .addOnFailureListener(failure -> {
                    System.out.println("Failure happen anjaayy");
                    System.out.println(failure);
                    failure.printStackTrace();
                    if (failure instanceof FirebaseAuthInvalidUserException) {
                        _loginState.postValue(LoginState.INVALID_CREDENTIAL);
                    } else {
                        _loginState.postValue(LoginState.FAILED);
                    }
                });
    }

    public Role getUserRole() {
        return userRole;
    }
}
