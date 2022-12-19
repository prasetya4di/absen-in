package id.ac.stiki.doleno.absenin.repository.impl;

import java.util.List;

import javax.inject.Inject;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

public class AbsentRepositoryImpl implements AbsentRepository {
    private final AbsentDao absentDao;

    @Inject
    public AbsentRepositoryImpl(AbsentDao absentDao) {
        this.absentDao = absentDao;
    }

    @Override
    public void newAbsent(Absent absent) {
        absentDao.insert(absent);
    }

    @Override
    public List<Absent> getAllAbsentHistory() {
        return absentDao.getAll();
    }

    @Override
    public Absent getAbsentHistoryById(int id) {
        return absentDao.getById(id);
    }
}
