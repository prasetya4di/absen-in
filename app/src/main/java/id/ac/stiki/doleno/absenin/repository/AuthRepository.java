package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthRepository {
    Task<AuthResult> register(String email, String password);

    Task<AuthResult> login(String email, String password);

    Task<AuthResult> registerWithGmail();

    Task<AuthResult> loginWithGmail();

    Boolean isLoggedIn();
}
