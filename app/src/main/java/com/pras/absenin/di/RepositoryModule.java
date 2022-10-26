package com.pras.absenin.di;

import com.pras.absenin.data.source.local.AbsentDao;
import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.repository.impl.AbsentRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public AbsentRepository provideAbsentRepository(AbsentDao absentDao) {
        return new AbsentRepositoryImpl(absentDao);
    }
}
