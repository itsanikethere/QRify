package io.github.itsanikethere.qrify.rest;

import io.github.itsanikethere.qrify.exception.QueryParameterException;
import io.github.itsanikethere.qrify.rest.dto.QRCodeRequest;
import io.github.itsanikethere.qrify.service.QRCodeService;

import org.apache.commons.lang3.StringUtils;

public class QRCodeRequestValidator {

    public static void validate(QRCodeRequest QRCodeRequest) {
        if (StringUtils.isBlank(QRCodeRequest.getVpa()))
            throw new QueryParameterException("'vpa' is missing");

        if (StringUtils.isBlank(QRCodeRequest.getName()))
            throw new QueryParameterException("'name' is missing");

        String notes = QRCodeRequest.getNotes();
        if (StringUtils.isNotBlank(notes) && notes.length() > 80)
            throw new QueryParameterException("'notes' cannot exceed 80 characters");

        if (QRCodeRequest.getAmount() != null && QRCodeRequest.getAmount() <= 0)
            throw new QueryParameterException("'amount' must be a positive number");

        if (QRCodeRequest.getWidth() != null && QRCodeRequest.getWidth() <= 0)
            throw new QueryParameterException("'width' must be a positive number");

        if (QRCodeRequest.getHeight() != null && QRCodeRequest.getHeight() <= 0)
            throw new QueryParameterException("'height' must be a positive number");

        String format = QRCodeRequest.getFormat();
        if (StringUtils.isNotBlank(format) && !QRCodeService.QR_VALID_FORMATS.contains(format.toLowerCase()))
            throw new QueryParameterException("'format' must be one of " + QRCodeService.QR_VALID_FORMATS);
    }

}
