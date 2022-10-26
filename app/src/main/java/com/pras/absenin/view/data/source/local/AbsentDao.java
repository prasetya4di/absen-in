package com.pras.absenin.view.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pras.absenin.view.data.entity.Absent;

import java.util.List;

@Dao
public interface AbsentDao {
    @Query("SELECT * FROM absent")
    List<Absent> getAll();

    @Query("SELECT * FROM absent WHERE uid = :absentId")
    List<Absent> getById(int absentId);

    @Insert
    void insert(Absent absent);
}
