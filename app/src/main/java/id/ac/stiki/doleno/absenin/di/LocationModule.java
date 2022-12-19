package id.ac.stiki.doleno.absenin.di;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ViewModelComponent.class)
public class LocationModule {
    @Provides
    public static FusedLocationProviderClient provideFusedLocationProviderClient(
            @ApplicationContext Context context
    ) {
        return LocationServices.getFusedLocationProviderClient(context);
    }
}
