package com.example.desafio.repository;

import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OcurrenceRepository extends JpaRepository<Ocurrence, Long> {
    List<Ocurrence> findByOrder_TrackingCodeOrderByOcurrenceTimeAsc(String trackingCode);

    Optional<Ocurrence> findTopByOrderOrderByOcurrenceTimeDesc(Order order);
}
