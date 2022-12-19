package id.ac.stiki.doleno.absenin.di;

import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import id.ac.stiki.doleno.absenin.data.database.AppDatabase;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;

@Module
@InstallIn(ViewModelComponent.class)
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
