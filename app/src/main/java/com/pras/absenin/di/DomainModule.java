package com.pras.absenin.di;

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
}
