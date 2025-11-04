package com.example.desafio.service;

import com.example.desafio.dto.OcurrenceTimeLineDTO;
import com.example.desafio.dto.TrackingStatusResponseDTO;
import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.Order;
import com.example.desafio.entity.enums.OcurrenceStatus;
import com.example.desafio.mapper.OcurrenceMapper;
import com.example.desafio.repository.OcurrenceRepository;
import com.example.desafio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcurrenceService {

    private final OcurrenceRepository ocurrenceRepository;
    private final OrderRepository orderRepository;
    private final OcurrenceMapper ocurrenceMapper;

    public Ocurrence registerOcurrence(RegisterOcurrenceDTO dto) {
        Order order = orderRepository.findByTrackingCode(dto.getTrackingCode())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Ocurrence lastOcurrence = ocurrenceRepository
                .findTopByOrderOrderByOcurrenceTimeDesc(order)
                .orElse(null);

        if (lastOcurrence != null && lastOcurrence.getStatus() == OcurrenceStatus.DELIVERED) {
            throw new IllegalStateException("It is not possible to register an ocurrence for a order already delivered.");
        }

        if (lastOcurrence != null && lastOcurrence.getStatus() == OcurrenceStatus.NOT_DELIVERED) {
            if (dto.getStatus() != OcurrenceStatus.OUT_FOR_DELIVERY) {
                throw new IllegalStateException("Após 'Não Entregue', o próximo status deve ser 'Saída para Entrega'.");
            }
        }

        Ocurrence ocurrence = ocurrenceMapper.toEntity(dto, order);

        return ocurrenceRepository.save(ocurrence);
    }

    public TrackingStatusResponseDTO getTrackingStatus(String trackingCode) {
        Order order = orderRepository.findAll().stream()
                .filter(o -> o.getTrackingCode().equalsIgnoreCase(trackingCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found with code: " + trackingCode));

        List<Ocurrence> ocurrences = ocurrenceRepository.findByOrder_TrackingCodeOrderByOcurrenceTimeAsc(trackingCode);

        if (ocurrences.isEmpty()) {
            throw new IllegalArgumentException("No ocurrence found with code: " + trackingCode);
        }

        List<OcurrenceTimeLineDTO> timeline = ocurrences.stream()
                .map(o -> new OcurrenceTimeLineDTO(o.getStatus(), o.getOcurrenceTime()))
                .toList();

        String currentStatus = ocurrences.getLast().getStatus().name();

        return new TrackingStatusResponseDTO(
                order.getTrackingCode(),
                order.getClientName(),
                order.getAddress(),
                timeline,
                currentStatus
        );
    }
}
