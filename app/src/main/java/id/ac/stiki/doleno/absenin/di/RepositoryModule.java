package id.ac.stiki.doleno.absenin.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.impl.AbsentRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao) {
        return new AbsentRepositoryImpl(absentDao);
    }
}
