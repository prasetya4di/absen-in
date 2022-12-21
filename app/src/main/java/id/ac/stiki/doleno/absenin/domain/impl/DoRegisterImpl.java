package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;

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
    public Task<Void> execute(String password, User user) {
        return authRepository
                .register(user.email, password)
                .addOnSuccessListener(documentSnapshot -> {
                    userRepository.delete();
                    userRepository.create(user);
                })
                .continueWith(task -> userRepository.post(user))
                .getResult();
    }
}
