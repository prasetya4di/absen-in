package id.ac.stiki.doleno.absenin.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.data.source.local.UserDao;
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore;
import id.ac.stiki.doleno.absenin.data.source.network.EventStore;
import id.ac.stiki.doleno.absenin.data.source.network.UserStore;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;
import id.ac.stiki.doleno.absenin.repository.EventRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;
import id.ac.stiki.doleno.absenin.repository.impl.AbsentRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.AuthRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.EventRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.UserRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao, AbsentStore absentStore, UserDao userDao) {
        return new AbsentRepositoryImpl(absentDao, userDao, absentStore);
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
    public AuthRepository provideAuthRepository(FirebaseFirestore firestore, FirebaseAuth fireauth) {
        return new AuthRepositoryImpl(fireauth, firestore);
    }
}
