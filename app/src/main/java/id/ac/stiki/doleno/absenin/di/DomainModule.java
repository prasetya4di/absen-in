package id.ac.stiki.doleno.absenin.di;

import com.google.android.gms.location.FusedLocationProviderClient;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.domain.AddEvent;
import id.ac.stiki.doleno.absenin.domain.CheckLoggedInStatus;
import id.ac.stiki.doleno.absenin.domain.DoAbsent;
import id.ac.stiki.doleno.absenin.domain.DoLogin;
import id.ac.stiki.doleno.absenin.domain.DoLogout;
import id.ac.stiki.doleno.absenin.domain.DoRegister;
import id.ac.stiki.doleno.absenin.domain.GetAbsentHistoryById;
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory;
import id.ac.stiki.doleno.absenin.domain.GetAllEvent;
import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation;
import id.ac.stiki.doleno.absenin.domain.GetEventById;
import id.ac.stiki.doleno.absenin.domain.impl.AddEventImpl;
import id.ac.stiki.doleno.absenin.domain.impl.CheckLoggedInStatusImpl;
import id.ac.stiki.doleno.absenin.domain.impl.DoAbsentImpl;
import id.ac.stiki.doleno.absenin.domain.impl.DoLoginImpl;
import id.ac.stiki.doleno.absenin.domain.impl.DoLogoutImpl;
import id.ac.stiki.doleno.absenin.domain.impl.DoRegisterImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetAbsentHistoryByIdImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetAllAbsentHistoryImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetAllEventImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetCurrentLocationImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetEventByIdImpl;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;
import id.ac.stiki.doleno.absenin.repository.EventRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

@Module
@InstallIn(ViewModelComponent.class)
public class DomainModule {

    @Provides
    public DoAbsent provideDoAbsent(AbsentRepository absentRepository) {
        return new DoAbsentImpl(absentRepository);
    }

    @Provides
    public GetAllAbsentHistory provideGetAllAbsent(AbsentRepository absentRepository) {
        return new GetAllAbsentHistoryImpl(absentRepository);
    }

    @Provides
    public GetAbsentHistoryById provideGetAbsentById(AbsentRepository absentRepository) {
        return new GetAbsentHistoryByIdImpl(absentRepository);
    }

    @Provides
    public GetCurrentLocation provideGetCurrentLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        return new GetCurrentLocationImpl(fusedLocationProviderClient);
    }

    @Provides
    public GetAllEvent provideGetAllEvent(EventRepository eventRepository) {
        return new GetAllEventImpl(eventRepository);
    }

    @Provides
    public GetEventById provideGetEventById(EventRepository eventRepository) {
        return new GetEventByIdImpl(eventRepository);
    }

    @Provides
    public AddEvent provideAddEvent(EventRepository eventRepository) {
        return new AddEventImpl(eventRepository);
    }

    @Provides
    public DoLogin provideDoLogin(AuthRepository authRepository, UserRepository userRepository) {
        return new DoLoginImpl(authRepository, userRepository);
    }

    @Provides
    public DoRegister provideDoRegister(AuthRepository authRepository, UserRepository userRepository) {
        return new DoRegisterImpl(authRepository, userRepository);
    }

    @Provides
    public CheckLoggedInStatus provideCheckLoggedIn(AuthRepository authRepository) {
        return new CheckLoggedInStatusImpl(authRepository);
    }

    @Provides
    public DoLogout provideLogout(UserRepository userRepository, AbsentRepository absentRepository, EventRepository eventRepository) {
        return new DoLogoutImpl(userRepository, absentRepository, eventRepository);
    }
}
