package com.pras.absenin.repository.impl;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.data.source.local.AbsentDao;
import com.pras.absenin.repository.AbsentRepository;

import java.util.List;

public class AbsentRepositoryImpl implements AbsentRepository {
    private final AbsentDao absentDao;

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
