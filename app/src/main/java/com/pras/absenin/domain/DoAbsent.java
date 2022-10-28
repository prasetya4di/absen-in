package com.pras.absenin.domain;

import com.google.android.gms.maps.model.LatLng;
import com.pras.absenin.util.qrcode.QRCodeResultStatus;

public interface DoAbsent {
    QRCodeResultStatus execute(String qrResult, LatLng currentLo);
}
