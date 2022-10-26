package com.pras.absenin.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pras.absenin.data.entity.Absent;

import java.util.List;

@Dao
public interface AbsentDao {
    @Query("SELECT * FROM absent")
    List<Absent> getAll();

    @Query("SELECT * FROM absent WHERE uid = :absentId")
    Absent getById(int absentId);

    @Insert
    void insert(Absent absent);
}
