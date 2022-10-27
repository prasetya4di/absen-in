package com.pras.absenin.util.qrcode;

public interface QRCodeFoundListener {
    void onQRCodeFound(QRCodeResultStatus status);

    void qrCodeNotFound();
}
