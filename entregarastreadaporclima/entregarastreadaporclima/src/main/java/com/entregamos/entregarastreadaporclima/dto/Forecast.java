package com.entregamos.entregarastreadaporclima.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Forecast {
    private List<ForecastDay> forecastday;
}