package io.github.itsanikethere.qrify.service;

import io.github.itsanikethere.qrify.rest.dto.QRCodeRequest;
import io.github.itsanikethere.qrify.rest.dto.QRCodeResponse;

import java.util.Arrays;
import java.util.List;

public interface QRCodeService {

    int QR_DEFAULT_WIDTH = 500;
    int QR_DEFAULT_HEIGHT = 500;

    String QR_FORMAT_PNG = "png";
    String QR_FORMAT_JPG = "jpg";

    String QR_DEFAULT_FORMAT = QR_FORMAT_PNG;

    List<String> QR_VALID_FORMATS =
            Arrays.asList(QR_FORMAT_PNG, QR_FORMAT_JPG);

    QRCodeResponse generateQRCode(QRCodeRequest QRCodeRequest);

}
