package com.aru.orderservice.services.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aru.orderservice.models.dto.GenericResponse;

@Component
public class ResponseEntityGenerator {

    public ResponseEntity<GenericResponse> generateErrorResponse(Exception exception) {
        GenericResponse genericResponse = new GenericResponse();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        genericResponse.setError(exception.getMessage());
        genericResponse.setStatus(statusCode);
        genericResponse.setError(exception.getMessage());

        return new ResponseEntity<>(genericResponse, new HttpHeaders(), httpStatus);
    }

    public ResponseEntity<GenericResponse> generateSuccessResponse(Object object) {
        GenericResponse genericResponse = new GenericResponse();
        int statusCode = HttpStatus.OK.value();
        genericResponse.setData(object);
        genericResponse.setStatus(statusCode);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
