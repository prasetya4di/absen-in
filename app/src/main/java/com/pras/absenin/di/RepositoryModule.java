package com.pras.absenin.di;

import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.repository.impl.AbsentRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class RepositoryModule {
    @Binds
    public abstract AbsentRepository provideAbsentRepository(AbsentRepositoryImpl absentRepository);
}
