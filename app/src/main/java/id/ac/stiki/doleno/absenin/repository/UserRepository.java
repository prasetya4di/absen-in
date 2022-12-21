package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface UserRepository {
    User getUser();

    Task<AuthResult> register(String email, String password, User user);

    Task<AuthResult> login(String email, String password);

    Task<AuthResult> registerWithGmail();

    Task<AuthResult> loginWithGmail();

    Task<AuthResult> updateUser(User user);
}
