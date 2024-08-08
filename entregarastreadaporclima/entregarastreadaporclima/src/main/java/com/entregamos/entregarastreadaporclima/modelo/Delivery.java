package com.entregamos.entregarastreadaporclima.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private double latitude;
    private double longitude;
    private LocalDate deliveryDate;

    public Delivery() {
    }

    public Delivery(String email, double latitude, double longitude, LocalDate deliveryDate) {
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deliveryDate = deliveryDate;
    }
}