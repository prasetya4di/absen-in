package id.ac.stiki.doleno.absenin.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ac.stiki.doleno.absenin.data.source.network.GlobalStore
import id.ac.stiki.doleno.absenin.domain.*
import id.ac.stiki.doleno.absenin.domain.impl.*
import id.ac.stiki.doleno.absenin.repository.*

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideDoAbsent(
        absentRepository: AbsentRepository,
        userRepository: UserRepository,
        eventParticipantRepository: EventParticipantRepository
    ): DoAbsent {
        return DoAbsentImpl(absentRepository, userRepository, eventParticipantRepository)
    }

    @Provides
    fun provideGetAllAbsent(absentRepository: AbsentRepository): GetAllAbsentHistory {
        return GetAllAbsentHistoryImpl(absentRepository)
    }

    @Provides
    fun provideGetAbsentById(absentRepository: AbsentRepository): GetAbsentHistoryById {
        return GetAbsentHistoryByIdImpl(absentRepository)
    }

    @Provides
    fun provideGetCurrentLocation(fusedLocationProviderClient: FusedLocationProviderClient): GetCurrentLocation {
        return GetCurrentLocationImpl(fusedLocationProviderClient)
    }

    @Provides
    fun provideGetAllEvent(eventRepository: EventRepository): GetAllEvent {
        return GetAllEventImpl(eventRepository)
    }

    @Provides
    fun provideGetEventById(eventRepository: EventRepository): GetEventById {
        return GetEventByIdImpl(eventRepository)
    }

    @Provides
    fun provideAddEvent(eventRepository: EventRepository, globalStore: GlobalStore): AddEvent {
        return AddEventImpl(eventRepository, globalStore)
    }

    @Provides
    fun provideDoLogin(authRepository: AuthRepository, userRepository: UserRepository): DoLogin {
        return DoLoginImpl(authRepository, userRepository)
    }

    @Provides
    fun provideDoRegister(
        authRepository: AuthRepository,
        userRepository: UserRepository
    ): DoRegister {
        return DoRegisterImpl(authRepository, userRepository)
    }

    @Provides
    fun provideCheckLoggedIn(authRepository: AuthRepository): CheckLoggedInStatus {
        return CheckLoggedInStatusImpl(authRepository)
    }

    @Provides
    fun provideLogout(
        userRepository: UserRepository,
        absentRepository: AbsentRepository,
        eventRepository: EventRepository
    ): DoLogout {
        return DoLogoutImpl(userRepository, absentRepository, eventRepository)
    }

    @Provides
    fun provideGetUserRole(userRepository: UserRepository): GetUserRole {
        return GetUserRoleImpl(userRepository)
    }

    @Provides
    fun provideGetUser(userRepository: UserRepository): GetUser {
        return GetUserImpl(userRepository)
    }

    @Provides
    fun provideFetchAllAbsentHistory(absentRepository: AbsentRepository): FetchAllAbsentHistory {
        return FetchAllAbsentHistoryImpl(absentRepository)
    }

    @Provides
    fun provideFetchAllEvent(repository: EventRepository): FetchAllEvent {
        return FetchAllEventImpl(repository)
    }

    @Provides
    fun provideFetchAllEventParticipant(repository: EventParticipantRepository): FetchAllEventParticipant {
        return FetchAllEventParticipantImpl(repository)
    }

    @Provides
    fun provideFetchAllEventByEmail(eventRepository: EventRepository): FetchAllEventByEmail {
        return FetchAllEventByEmailImpl(eventRepository)
    }

    @Provides
    fun provideRegisterEvent(
        absentRepository: AbsentRepository,
        eventParticipantRepository: EventParticipantRepository,
        globalStore: GlobalStore
    ): RegisterEvent {
        return RegisterEventImpl(absentRepository, eventParticipantRepository, globalStore)
    }

    @Provides
    fun provideGetAbsentByStatus(absentRepository: AbsentRepository): GetAbsentByStatus {
        return GetAbsentByStatusImpl(absentRepository)
    }

    @Provides
    fun provideCheckRegisteredStatus(eventParticipantRepository: EventParticipantRepository): CheckRegisteredStatus {
        return CheckRegisteredStatusImpl(eventParticipantRepository)
    }

    @Provides
    fun provideGenerateQrBitmap(): GenerateQrBitmap {
        return GenerateQrBitmapImpl()
    }

    @Provides
    fun provideGetEventParticipant(eventParticipantRepository: EventParticipantRepository): GetEventParticipant {
        return GetEventParticipantImpl(eventParticipantRepository)
    }

    @Provides
    fun provideDownloadQrCode(@ApplicationContext context: Context): DownloadQrCode {
        return DownloadQrCodeImpl(context)
    }

    @Provides
    fun provideCheckEventAvailability(eventRepository: EventRepository): CheckEventAvailability {
        return CheckEventAvailabilityImpl(eventRepository)
    }
}