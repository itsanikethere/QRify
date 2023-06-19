package io.github.itsanikethere.qrify.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QRCodeResponse {

    private String QRCode;
    private String format;
    private int width;
    private int height;

    public QRCodeResponse(String QRCode, String format, int width, int height) {
        this.QRCode = QRCode;
        this.format = format;
        this.width = width;
        this.height = height;
    }

    @JsonProperty("qr_code")
    public String getQRCode() {
        return QRCode;
    }

    @JsonProperty("qr_code")
    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
