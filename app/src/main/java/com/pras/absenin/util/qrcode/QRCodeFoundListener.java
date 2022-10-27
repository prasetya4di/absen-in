package com.pras.absenin.util.qrcode;

public interface QRCodeFoundListener {
    void onQRCodeFound(String result);

    void qrCodeNotFound();
}
