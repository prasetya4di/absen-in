package com.pras.absenin.domain.impl;

import static com.pras.absenin.util.qrcode.QRCodeResultStatus.INVALID;
import static com.pras.absenin.util.qrcode.QRCodeResultStatus.VALID;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.gson.Gson;
import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.DoAbsent;
import com.pras.absenin.repository.AbsentRepository;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;

import javax.inject.Inject;

public class DoAbsentImpl implements DoAbsent {
    private final AbsentRepository absentRepository;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private final Context context;

    @Inject
    public DoAbsentImpl(
            Context context,
            AbsentRepository absentRepository,
            FusedLocationProviderClient fusedLocationProviderClient
    ) {
        this.context = context;
        this.absentRepository = absentRepository;
        this.fusedLocationProviderClient = fusedLocationProviderClient;
    }

    @Override
    public QRCodeResultStatus execute(String qrResult) {
        try {
            Absent absent = new Gson().fromJson(qrResult, Absent.class);
            absentRepository.newAbsent(absent);
            return VALID;
        } catch (Exception e) {
            return INVALID;
        }
    }
}
