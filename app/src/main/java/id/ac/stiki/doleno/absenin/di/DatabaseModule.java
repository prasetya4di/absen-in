package id.ac.stiki.doleno.absenin.di;

import android.content.Context;

import androidx.room.Room;

import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import id.ac.stiki.doleno.absenin.data.database.AppDatabase;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
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
    public static FirebaseFirestore provideFireStore() {
        return FirebaseFirestore.getInstance();
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
}
