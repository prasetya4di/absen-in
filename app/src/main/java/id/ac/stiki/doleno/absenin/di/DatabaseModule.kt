package id.ac.stiki.doleno.absenin.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ac.stiki.doleno.absenin.data.database.AppDatabase
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao
import id.ac.stiki.doleno.absenin.data.source.local.EventDao
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao
import id.ac.stiki.doleno.absenin.data.source.local.UserDao

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {
    @Provides
    fun provideAbsentDao(appDatabase: AppDatabase): AbsentDao {
        return appDatabase.absentDao()
    }

    @Provides
    fun provideEventDao(appDatabase: AppDatabase): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideEventParticipantDao(appDatabase: AppDatabase): EventParticipantDao {
        return appDatabase.eventParticipantDao()
    }

    companion object {
        @JvmStatic
        @Provides
        fun provideAppDatabase(
            @ApplicationContext context: Context
        ): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "db_absenin")
                .build()
        }

        @JvmStatic
        @Provides
        fun provideFireAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }
}