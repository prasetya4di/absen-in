package id.ac.stiki.doleno.absenin.repository.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.inject.Inject;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

public class AbsentRepositoryImpl implements AbsentRepository {
    private final AbsentDao absentDao;
    private final UserDao userDao;
    private final AbsentStore absentStore;

    @Inject
    public AbsentRepositoryImpl(AbsentDao absentDao, UserDao userDao, AbsentStore absentStore) {
        this.absentDao = absentDao;
        this.absentStore = absentStore;
        this.userDao = userDao;
    }

    @Override
    public void create(Absent absent) {
        absentDao.insert(absent);
    }

    @Override
    public List<Absent> read() {
        return absentDao.getAll();
    }

    @Override
    public Absent read(int id) {
        return absentDao.getById(id);
    }

    @Override
    public Task<Void> post(Absent absent) {
        return absentStore.createAbsent(absent, userDao.getUser().email);
    }

    @Override
    public Task<QuerySnapshot> get() {
        return absentStore.get(userDao.getUser().email);
    }
}
