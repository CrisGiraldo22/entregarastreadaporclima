package com.entregamos.entregarastreadaporclima.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<ErrorResponse> handleWeatherServiceException(WeatherServiceException e) {
        ErrorResponse error = new ErrorResponse("Weather Service Error", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}