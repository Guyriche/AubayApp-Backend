package com.App.QCM.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class QcmUtils {

    public QcmUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
