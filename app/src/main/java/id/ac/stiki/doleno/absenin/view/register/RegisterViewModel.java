package id.ac.stiki.doleno.absenin.view.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.DoRegister;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private final DoRegister doRegister;
    private final MutableLiveData<RegisterState> _registerState = new MutableLiveData<>();
    LiveData<RegisterState> registerState = _registerState;

    @Inject
    public RegisterViewModel(DoRegister doRegister) {
        this.doRegister = doRegister;
    }

    public void register(String password, User user) {
        _registerState.postValue(RegisterState.LOADING);
        doRegister.execute(password, user)
                .addOnSuccessListener(task -> _registerState.postValue(RegisterState.SUCCESS))
                .addOnFailureListener(failure -> {
                    if (failure instanceof FirebaseAuthUserCollisionException) {
                        _registerState.postValue(RegisterState.EMAIL_TAKEN);
                    } else if (failure instanceof FirebaseAuthWeakPasswordException) {
                        _registerState.postValue(RegisterState.PASSWORD_WEAK);
                    } else {
                        _registerState.postValue(RegisterState.FAILED);
                    }
                });
    }
}
