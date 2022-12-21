package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.DoRegister;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class DoRegisterImpl implements DoRegister {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    public DoRegisterImpl(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task<AuthResult> execute(String password, User user) {
        return authRepository
                .register(user.email, password)
                .addOnSuccessListener(documentSnapshot -> {
                    userRepository.delete();
                    userRepository.create(user);
                });
    }
}
