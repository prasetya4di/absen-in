package com.pras.absenin.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.data.source.local.AbsentDao;

@Database(entities = {Absent.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbsentDao absentDao();
}
