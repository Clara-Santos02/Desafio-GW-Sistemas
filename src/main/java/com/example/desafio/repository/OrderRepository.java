package com.example.desafio.repository;

import com.example.desafio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByTrackingCode(String trackingCode);

    Optional<Order> findByTrackingCode(String trackingCode);
}
