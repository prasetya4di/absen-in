package id.ac.stiki.doleno.absenin.util.qrcode;

public interface QRCodeFoundListener {
    void onQRCodeFound(String result);

    void qrCodeNotFound();
}
