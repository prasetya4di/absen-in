package id.ac.stiki.doleno.absenin.repository.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;

    public UserRepositoryImpl(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Task<AuthResult> register(String email, String password, User user) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> login(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> registerWithGmail() {
        return null;
    }

    @Override
    public Task<AuthResult> loginWithGmail() {
        return null;
    }

    @Override
    public Task<AuthResult> updateUser(User user) {
        return null;
    }
}
