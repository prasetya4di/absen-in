package id.ac.stiki.doleno.absenin.di;

import android.content.Context;

import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import id.ac.stiki.doleno.absenin.data.database.AppDatabase;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;

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
    public static FirebaseAuth provideFireAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public AbsentDao provideAbsentDao(AppDatabase appDatabase) {
        return appDatabase.absentDao();
    }

    @Provides
    public EventDao provideEventDao(AppDatabase appDatabase) {
        return appDatabase.eventDao();
    }

    @Provides
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    public EventParticipantDao provideEventParticipantDao(AppDatabase appDatabase) {
        return appDatabase.eventParticipantDao();
    }
}
