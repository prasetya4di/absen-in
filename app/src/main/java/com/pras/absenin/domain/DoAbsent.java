package com.pras.absenin.domain;

import com.google.android.gms.maps.model.LatLng;
import com.pras.absenin.data.entity.Absent;

public interface DoAbsent {
    Boolean execute(Absent absent, LatLng currentLocation);
}
