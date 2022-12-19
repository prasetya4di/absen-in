package id.ac.stiki.doleno.absenin.di;

import com.google.android.gms.location.FusedLocationProviderClient;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.domain.DoAbsent;
import id.ac.stiki.doleno.absenin.domain.GetAbsentHistoryById;
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory;
import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation;
import id.ac.stiki.doleno.absenin.domain.impl.DoAbsentImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetAbsentHistoryByIdImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetAllAbsentHistoryImpl;
import id.ac.stiki.doleno.absenin.domain.impl.GetCurrentLocationImpl;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

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

    @Provides
    public GetCurrentLocation provideGetCurrentLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        return new GetCurrentLocationImpl(fusedLocationProviderClient);
    }
}
