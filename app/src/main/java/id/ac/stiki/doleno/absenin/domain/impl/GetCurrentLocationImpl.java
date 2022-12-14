package id.ac.stiki.doleno.absenin.domain.impl;

import android.annotation.SuppressLint;
import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import id.ac.stiki.doleno.absenin.domain.GetCurrentLocation;

public class GetCurrentLocationImpl implements GetCurrentLocation {
    private final FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    public GetCurrentLocationImpl(FusedLocationProviderClient fusedLocationProviderClient) {
        this.fusedLocationProviderClient = fusedLocationProviderClient;
    }

    @SuppressLint("MissingPermission")
    @Override
    public Task<Location> execute() {
        return fusedLocationProviderClient
                .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
                    @NonNull
                    @Override
                    public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                        return this;
                    }

                    @Override
                    public boolean isCancellationRequested() {
                        return false;
                    }
                });
    }
}
