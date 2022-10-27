package com.pras.absenin.di;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.domain.GetAbsentHistoryById;
import com.pras.absenin.domain.GetAllAbsentHistory;
import com.pras.absenin.domain.impl.DoAbsentImpl;
import com.pras.absenin.domain.impl.GetAbsentHistoryByIdImpl;
import com.pras.absenin.domain.impl.GetAllAbsentHistoryImpl;
import com.pras.absenin.repository.AbsentRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ViewModelComponent.class)
public class DomainModule {

    @Provides
    public DoAbsent provideDoAbsent(
            @ApplicationContext Context context,
            AbsentRepository absentRepository,
            FusedLocationProviderClient fusedLocationProviderClient) {
        return new DoAbsentImpl(context, absentRepository, fusedLocationProviderClient);
    }

    @Provides
    public GetAllAbsentHistory provideGetAllAbsent(AbsentRepository absentRepository) {
        return new GetAllAbsentHistoryImpl(absentRepository);
    }

    @Provides
    public GetAbsentHistoryById provideGetAbsentById(AbsentRepository absentRepository) {
        return new GetAbsentHistoryByIdImpl(absentRepository);
    }
}
