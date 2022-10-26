package com.pras.absenin.domain.impl;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.repository.AbsentRepository;

import javax.inject.Inject;

public class DoAbsentImpl implements DoAbsent {
    private final AbsentRepository absentRepository;

    @Inject
    public DoAbsentImpl(AbsentRepository absentRepository) {
        this.absentRepository = absentRepository;
    }

    @Override
    public Boolean execute(Absent absent, LatLng currentLocation) {
        double distance = SphericalUtil.computeDistanceBetween(absent.location, currentLocation) / 1000;
        if (distance < 0.1) {
            absentRepository.newAbsent(absent);
            return true;
        } else {
            return false;
        }
    }
}
