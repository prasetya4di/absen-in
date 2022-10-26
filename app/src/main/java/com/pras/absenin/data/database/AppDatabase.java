package com.pras.absenin.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.data.source.local.AbsentDao;
import com.pras.absenin.util.converters.DateConverter;
import com.pras.absenin.util.converters.LatLngConverter;

@Database(entities = {Absent.class}, version = 1)
@TypeConverters({DateConverter.class, LatLngConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbsentDao absentDao();
}
