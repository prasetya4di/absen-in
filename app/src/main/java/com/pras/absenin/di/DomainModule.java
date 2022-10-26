package com.pras.absenin.di;

import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.domain.GetAbsentHistoryById;
import com.pras.absenin.domain.GetAllAbsentHistory;
import com.pras.absenin.domain.impl.DoAbsentImpl;
import com.pras.absenin.domain.impl.GetAbsentHistoryByIdImpl;
import com.pras.absenin.domain.impl.GetAllAbsentHistoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class DomainModule {
    @Binds
    public abstract DoAbsent provideDoAbsent(DoAbsentImpl doAbsent);

    @Binds
    public abstract GetAllAbsentHistory provideGetAllAbsent(GetAllAbsentHistoryImpl getAllAbsentHistory);

    @Binds
    public abstract GetAbsentHistoryById provideGetAbsentById(GetAbsentHistoryByIdImpl getAbsentHistoryById);
}
