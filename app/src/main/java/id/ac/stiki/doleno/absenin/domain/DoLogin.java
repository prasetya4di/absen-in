package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface DoLogin {
    Task<AuthResult> execute(String email, String password);
}
