package com.pras.absenin.di;

import android.content.Context;

import androidx.room.Room;

import com.pras.absenin.data.database.AppDatabase;
import com.pras.absenin.data.source.local.AbsentDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    public static AppDatabase provideAppDatabase(
            @ApplicationContext Context context
    ) {
        return Room
                .databaseBuilder(context, AppDatabase.class, "db_absenin")
                .build();
    }

    @Provides
    public AbsentDao provideAbsentDao(AppDatabase appDatabase) {
        return appDatabase.absentDao();
    }
}
