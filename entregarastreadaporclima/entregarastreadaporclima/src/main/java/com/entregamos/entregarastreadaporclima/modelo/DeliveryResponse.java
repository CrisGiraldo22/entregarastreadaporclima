package com.entregamos.entregarastreadaporclima.modelo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryResponse {
    private final int forecastCode;
    private final String forecastDescription;
    private final boolean customerNotification;
}