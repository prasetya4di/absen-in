package com.pras.absenin.repository;

import com.pras.absenin.data.entity.Absent;

import java.util.List;

public interface AbsentRepository {
    void newAbsent(Absent absent);

    List<Absent> getAllAbsentHistory();

    Absent getAbsentHistoryById(int id);
}
