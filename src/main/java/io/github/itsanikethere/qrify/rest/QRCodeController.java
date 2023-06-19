package io.github.itsanikethere.qrify.rest;

import io.github.itsanikethere.qrify.rest.dto.QRCodeRequest;
import io.github.itsanikethere.qrify.rest.dto.QRCodeResponse;
import io.github.itsanikethere.qrify.service.QRCodeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QRCodeController {

    private final QRCodeService QRCodeService;

    QRCodeController(QRCodeService QRCodeService) {
        this.QRCodeService = QRCodeService;
    }

    @GetMapping("/QRify")
    public ResponseEntity<QRCodeResponse> generateQRCode(@RequestBody QRCodeRequest QRCodeRequest) {
        QRCodeRequestValidator.validate(QRCodeRequest);
        return ResponseEntity.ok().body(QRCodeService.generateQRCode(QRCodeRequest));
    }

}
