package id.ac.stiki.doleno.absenin.repository.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;
import id.ac.stiki.doleno.absenin.data.source.network.UserStore;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final UserStore userStore;

    public UserRepositoryImpl(UserDao userDao, UserStore userStore) {
        this.userDao = userDao;
        this.userStore = userStore;
    }

    @Override
    public void create(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User read() {
        return userDao.getUser();
    }

    @Override
    public Task<Void> post(User user) {
        return userStore.createUser(user);
    }

    @Override
    public Task<Void> put(User user) {
        return userStore.updateUser(user);
    }

    @Override
    public Task<DocumentSnapshot> get() {
        return userStore.getUser(userDao.getUser().email);
    }
}
