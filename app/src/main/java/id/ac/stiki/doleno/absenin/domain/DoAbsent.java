package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.maps.model.LatLng;

import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus;

public interface DoAbsent {
    QRCodeResultStatus execute(String qrResult, LatLng currentLo);
}
