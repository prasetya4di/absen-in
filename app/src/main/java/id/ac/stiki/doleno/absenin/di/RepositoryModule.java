package id.ac.stiki.doleno.absenin.di;

import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore;
import id.ac.stiki.doleno.absenin.data.source.network.EventParticipantStore;
import id.ac.stiki.doleno.absenin.data.source.network.EventStore;
import id.ac.stiki.doleno.absenin.data.source.network.UserStore;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;
import id.ac.stiki.doleno.absenin.repository.EventRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;
import id.ac.stiki.doleno.absenin.repository.impl.AbsentRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.AuthRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.EventParticipantRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.EventRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.UserRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao, AbsentStore absentStore) {
        return new AbsentRepositoryImpl(absentDao, absentStore);
    }

    @Provides
    public EventRepository provideEventRepository(EventDao eventDao, EventStore eventStore) {
        return new EventRepositoryImpl(eventDao, eventStore);
    }

    @Provides
    public UserRepository provideUserRepository(UserDao userDao, UserStore userStore) {
        return new UserRepositoryImpl(userDao, userStore);
    }

    @Provides
    public AuthRepository provideAuthRepository(FirebaseAuth fireauth, UserRepository userRepository) {
        return new AuthRepositoryImpl(fireauth, userRepository);
    }

    @Provides
    public EventParticipantRepository provideEventParticipantRepository(EventParticipantDao eventParticipantDao, EventParticipantStore eventParticipantStore) {
        return new EventParticipantRepositoryImpl(eventParticipantDao, eventParticipantStore);
    }
}
