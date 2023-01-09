package id.ac.stiki.doleno.absenin.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao
import id.ac.stiki.doleno.absenin.data.source.local.EventDao
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao
import id.ac.stiki.doleno.absenin.data.source.local.UserDao
import id.ac.stiki.doleno.absenin.data.source.network.*
import id.ac.stiki.doleno.absenin.repository.*
import id.ac.stiki.doleno.absenin.repository.impl.*

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideAbsentRepository(
        absentDao: AbsentDao,
        absentStore: AbsentStore
    ): AbsentRepository {
        return AbsentRepositoryImpl(absentDao, absentStore)
    }

    @Provides
    fun provideEventRepository(eventDao: EventDao, eventStore: EventStore): EventRepository {
        return EventRepositoryImpl(eventDao, eventStore)
    }

    @Provides
    fun provideUserRepository(userDao: UserDao, userStore: UserStore): UserRepository {
        return UserRepositoryImpl(userDao, userStore)
    }

    @Provides
    fun provideAuthRepository(
        fireauth: FirebaseAuth,
        userRepository: UserRepository
    ): AuthRepository {
        return AuthRepositoryImpl(fireauth, userRepository)
    }

    @Provides
    fun provideEventParticipantRepository(
        eventParticipantDao: EventParticipantDao,
        eventParticipantStore: EventParticipantStore
    ): EventParticipantRepository {
        return EventParticipantRepositoryImpl(eventParticipantDao, eventParticipantStore)
    }
}