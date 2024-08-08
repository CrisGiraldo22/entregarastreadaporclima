package com.entregamos.entregarastreadaporclima.repositorio;


import com.entregamos.entregarastreadaporclima.modelo.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
