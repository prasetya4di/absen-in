package id.ac.stiki.doleno.absenin.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.EventRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;
import id.ac.stiki.doleno.absenin.repository.impl.AbsentRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.EventRepositoryImpl;
import id.ac.stiki.doleno.absenin.repository.impl.UserRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao) {
        return new AbsentRepositoryImpl(absentDao);
    }

    @Provides
    public EventRepository provideEventRepository(EventDao eventDao) {
        return new EventRepositoryImpl(eventDao);
    }

    @Provides
    public UserRepository provideUserRepository(FirebaseFirestore firestore, FirebaseAuth fireauth) {
        return new UserRepositoryImpl(fireauth, firestore);
    }
}
