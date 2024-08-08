package com.entregamos.entregarastreadaporclima.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseDTO {
    private int code;
    private String description;
    private boolean notification;

    // Constructor, getters y setters generados por Lombok

    public WeatherResponseDTO(int code, String description, boolean notification) {
        this.code = code;
        this.description = description;
        this.notification = notification;
    }
}
