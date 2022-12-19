package id.ac.stiki.doleno.absenin.repository;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface AbsentRepository {
    void newAbsent(Absent absent);

    List<Absent> getAllAbsentHistory();

    Absent getAbsentHistoryById(int id);
}
