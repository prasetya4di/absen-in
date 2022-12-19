package id.ac.stiki.doleno.absenin.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

@Dao
public interface AbsentDao {
    @Query("SELECT * FROM absent")
    List<Absent> getAll();

    @Query("SELECT * FROM absent WHERE uid = :absentId")
    Absent getById(int absentId);

    @Insert
    void insert(Absent absent);
}