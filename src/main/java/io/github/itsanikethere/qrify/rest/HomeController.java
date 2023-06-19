package io.github.itsanikethere.qrify.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final static String REPOSITORY_URL = "https://github.com/itsanikethere/QRify";

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String message = String.format(
                "Greetings from QRify! For API documentation, please visit %s.", REPOSITORY_URL
        );

        return ResponseEntity.ok().body(message);
    }

}
