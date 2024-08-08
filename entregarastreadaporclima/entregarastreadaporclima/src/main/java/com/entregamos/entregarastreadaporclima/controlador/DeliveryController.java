package com.entregamos.entregarastreadaporclima.controlador;

import com.entregamos.entregarastreadaporclima.dto.DeliveryRequestDTO;
import com.entregamos.entregarastreadaporclima.dto.WeatherResponseDTO;
import com.entregamos.entregarastreadaporclima.excepciones.WeatherServiceException;
import com.entregamos.entregarastreadaporclima.modelo.Delivery;
import com.entregamos.entregarastreadaporclima.repositorio.DeliveryRepository;
import com.entregamos.entregarastreadaporclima.servicio.EmailService;
import com.entregamos.entregarastreadaporclima.servicio.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final WeatherService weatherService;
    private final DeliveryRepository deliveryRepository;
    private final EmailService emailService;

    @Autowired
    public DeliveryController(WeatherService weatherService, DeliveryRepository deliveryRepository, EmailService emailService) {
        this.weatherService = weatherService;
        this.deliveryRepository = deliveryRepository;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryRequestDTO request) {
        Delivery newDelivery = new Delivery(
                request.email(),
                request.latitude(),
                request.longitude(),
                request.deliveryDate()
        );
        deliveryRepository.save(newDelivery);
        return ResponseEntity.ok(newDelivery);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") Long id, @RequestBody DeliveryRequestDTO request) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);

        return delivery
                .map(existingDelivery -> {
                    existingDelivery.setEmail(request.email());
                    existingDelivery.setLatitude(request.latitude());
                    existingDelivery.setLongitude(request.longitude());
                    existingDelivery.setDeliveryDate(request.deliveryDate());
                    deliveryRepository.save(existingDelivery);
                    return ResponseEntity.ok(existingDelivery);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable("id") Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<Delivery>> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        if (deliveries.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(deliveries);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkDelay(
            @RequestParam String email,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        try {
            WeatherResponseDTO response = weatherService.checkDelay(latitude, longitude);
            if (response.isNotification()) {
                emailService.sendDelayNotification(email, response.getDescription());
            }
            return ResponseEntity.ok(response);
        } catch (WeatherServiceException e) {
            // Manejar excepción del servicio meteorológico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al consultar el servicio meteorológico");
        } catch (Exception e) {
            // Manejar otras excepciones
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al procesar la solicitud");
        }
    }
}
