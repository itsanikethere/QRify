package io.github.itsanikethere.qrify.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import io.github.itsanikethere.qrify.exception.QRGenerationException;
import io.github.itsanikethere.qrify.rest.dto.QRCodeRequest;
import io.github.itsanikethere.qrify.rest.dto.QRCodeResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private static final String URL_PREFIX = "upi://pay?";
    private static final String PARAM_PA = "pa";
    private static final String PARAM_PN = "pn";
    private static final String PARAM_AM = "am";
    private static final String PARAM_TN = "tn";

    @Override
    public QRCodeResponse generateQRCode(QRCodeRequest QRCodeRequest) {
        String vpa = QRCodeRequest.getVpa();
        String name = QRCodeRequest.getName();
        String notes = QRCodeRequest.getNotes();
        Double amount = QRCodeRequest.getAmount();
        Integer width = QRCodeRequest.getWidth();
        Integer height = QRCodeRequest.getHeight();
        String format = QRCodeRequest.getFormat();

        if (width == null)
            width = QRCodeService.QR_DEFAULT_WIDTH;

        if (height == null)
            height = QRCodeService.QR_DEFAULT_HEIGHT;

        if (StringUtils.isBlank(format))
            format = QRCodeService.QR_DEFAULT_FORMAT;

        String URI = buildURI(vpa, name, notes, amount);

        BufferedImage QRCode;
        try {
            QRCode = generateQRCode(URI, width, height);
        } catch (WriterException e) {
            throw new QRGenerationException("Unable to generate QR code");
        }

        String base64String;
        try {
            base64String = encodeQRCode(QRCode, format);
        } catch (IOException e) {
            throw new QRGenerationException("Unable to encode QR code");
        }

        return new QRCodeResponse(base64String, format, width, height);
    }

    private String buildURI(String vpa, String name, String notes, Double amount) {
        StringBuilder builder = new StringBuilder(URL_PREFIX)
                .append(PARAM_PA + "=").append(vpa)
                .append("&")
                .append(PARAM_PN + "=").append(name);

        if (StringUtils.isNotBlank(notes))
            builder.append("&").append(PARAM_TN + "=").append(notes);

        if (amount != null)
            builder.append("&").append(PARAM_AM + "=").append(String.format("%.2f", amount));

        return builder.toString();
    }

    private BufferedImage generateQRCode(String URI, Integer width, Integer height) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix = writer.encode(URI, BarcodeFormat.QR_CODE, width, height);

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    private String encodeQRCode(BufferedImage QRCode, String format) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            ImageIO.write(QRCode, format, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(bytes);
        }
    }

}
