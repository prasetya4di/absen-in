package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface UserRepository {
    User getUser();

    Task<AuthResult> register(String email, String password, OnCompleteListener<AuthResult> onCompleteListener, OnFailureListener onFailureListener);

    Task<AuthResult> login(String email, String password, OnCompleteListener<AuthResult> onCompleteListener, OnFailureListener onFailureListener);

    Task<AuthResult> registerWithGmail();

    Task<AuthResult> loginWithGmail();

    void updateUser(User user);
}
