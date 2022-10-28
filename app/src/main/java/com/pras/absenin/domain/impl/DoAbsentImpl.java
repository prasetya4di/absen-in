package com.pras.absenin.domain.impl;

import static com.pras.absenin.util.qrcode.QRCodeResultStatus.INVALID;
import static com.pras.absenin.util.qrcode.QRCodeResultStatus.VALID;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;

import javax.inject.Inject;

public class DoAbsentImpl implements DoAbsent {
    private final AbsentRepository absentRepository;

    @Inject
    public DoAbsentImpl(
            AbsentRepository absentRepository
    ) {
        this.absentRepository = absentRepository;
    }

    @Override
    public QRCodeResultStatus execute(String qrResult, LatLng currentLoc) {
        try {
            Absent absent = new Gson().fromJson(qrResult, Absent.class);
            absentRepository.newAbsent(absent);
            return VALID;
        } catch (Exception e) {
            e.printStackTrace();
            return INVALID;
        }
    }
}
