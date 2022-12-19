package id.ac.stiki.doleno.absenin.domain;

import android.location.Location;

import com.google.android.gms.tasks.Task;

public interface GetCurrentLocation {
    Task<Location> execute();
}
