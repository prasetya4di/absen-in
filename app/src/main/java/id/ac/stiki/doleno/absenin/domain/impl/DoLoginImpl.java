package id.ac.stiki.doleno.absenin.domain.impl;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.DoLogin;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class DoLoginImpl implements DoLogin {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    public DoLoginImpl(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task<DocumentSnapshot> execute(String email, String password) {
        return authRepository
                .login(email, password)
                .continueWithTask(task -> userRepository.get(email))
                .addOnSuccessListener(dataUser -> {
                    if (dataUser.exists()) {
                        AsyncTask.execute(() -> {
                            userRepository.delete();
                            userRepository.create(new User(dataUser.getData()));
                        });
                    }
                });
    }
}
