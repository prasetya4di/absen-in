package com.pras.absenin.domain.impl;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;

import javax.inject.Inject;

public class DoAbsentImpl implements DoAbsent {
    private final AbsentRepository absentRepository;
    private final FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    public DoAbsentImpl(AbsentRepository absentRepository, FusedLocationProviderClient fusedLocationProviderClient) {
        this.absentRepository = absentRepository;
        this.fusedLocationProviderClient = fusedLocationProviderClient;
    }

    @Override
    public QRCodeResultStatus execute(String qrResult) {
        return QRCodeResultStatus.INVALID;
    }
}
