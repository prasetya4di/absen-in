package com.pras.absenin.domain;

import com.pras.absenin.util.qrcode.QRCodeResultStatus;

public interface DoAbsent {
    QRCodeResultStatus execute(String qrResult);
}
