package com.entregamos.entregarastreadaporclima.dto;

import java.time.LocalDate;

public record DeliveryRequestDTO(String email, double latitude, double longitude, LocalDate deliveryDate) {
}
