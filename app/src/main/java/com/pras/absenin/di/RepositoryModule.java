package com.pras.absenin.di;

import com.pras.absenin.data.source.local.AbsentDao;
import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.repository.impl.AbsentRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao) {
        return new AbsentRepositoryImpl(absentDao);
    }
}
