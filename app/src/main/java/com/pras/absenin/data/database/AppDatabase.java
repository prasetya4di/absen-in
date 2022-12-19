package com.pras.absenin.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.data.entity.User;
import com.pras.absenin.data.source.local.AbsentDao;
import com.pras.absenin.data.source.local.UserDao;
import com.pras.absenin.util.converters.DateConverter;
import com.pras.absenin.util.converters.LatLngConverter;
import com.pras.absenin.util.converters.RoleConverter;

@Database(entities = {Absent.class, User.class}, version = 1)
@TypeConverters({DateConverter.class, LatLngConverter.class, RoleConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbsentDao absentDao();

    public abstract UserDao userDao();
}
