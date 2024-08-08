package com.entregamos.entregarastreadaporclima.servicio;

import com.entregamos.entregarastreadaporclima.dto.ForecastDay;
import com.entregamos.entregarastreadaporclima.dto.ForecastResponse;
import com.entregamos.entregarastreadaporclima.dto.WeatherResponseDTO;
import com.entregamos.entregarastreadaporclima.excepciones.WeatherServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    private static final List<Integer> NOTIFICATION_CODES = Arrays.asList(1186, 1189, 1192, 1195);
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponseDTO checkDelay(double latitude, double longitude) {
        try {
            // URL actualizada con los parámetros de latitud y longitud
            String apiUrl = String.format("https://api.weatherapi.com/v1/forecast.json?key=83b7c8c1fa89489fa81224129240506&q=%f,%f&days=2&aqi=no&alerts=no&lang=es", latitude, longitude);
            ForecastResponse forecastResponse = restTemplate.getForObject(apiUrl, ForecastResponse.class);

            if (forecastResponse == null || forecastResponse.getForecast() == null ||
                    forecastResponse.getForecast().getForecastday() == null ||
                    forecastResponse.getForecast().getForecastday().size() < 2) {
                throw new IllegalStateException("Respuesta del pronóstico inválida.");
            }

            ForecastDay forecastDay = forecastResponse.getForecast().getForecastday().get(1); // Día siguiente
            int forecastCode = forecastDay.getDay().getCondition().getCode();
            String forecastDescription = forecastDay.getDay().getCondition().getText();
            boolean notifyCustomer = NOTIFICATION_CODES.contains(forecastCode);

            return new WeatherResponseDTO(forecastCode, forecastDescription, notifyCustomer);
        } catch (RestClientException e) {
            throw new WeatherServiceException("Error al consultar el servicio meteorológico", e);
        } catch (Exception e) {
            throw new WeatherServiceException("Error inesperado al consultar el servicio meteorológico", e);
        }
    }
}
