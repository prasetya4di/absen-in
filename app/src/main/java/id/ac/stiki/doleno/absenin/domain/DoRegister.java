package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface DoRegister {
    Task<AuthResult> execute(String password, User user);
}
