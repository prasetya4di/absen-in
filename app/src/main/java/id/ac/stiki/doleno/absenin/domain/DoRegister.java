package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface DoRegister {
    Task<Void> execute(String password, User user);
}
