package com.example.desafio.service;

import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.dto.TrackingStatusResponseDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.Order;
import com.example.desafio.entity.enums.OcurrenceStatus;
import com.example.desafio.mapper.OcurrenceMapper;
import com.example.desafio.repository.OcurrenceRepository;
import com.example.desafio.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OcurrenceServiceTest {

    @Mock
    private OcurrenceRepository ocurrenceRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OcurrenceMapper ocurrenceMapper;

    @InjectMocks
    private OcurrenceService ocurrenceService;

    @Test
    void registerOcurrenceWithSuccess() {
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("T123");
        dto.setStatus(OcurrenceStatus.IN_TRANSIT);

        Order order = new Order();
        order.setTrackingCode("T123");

        Ocurrence ocurrence = new Ocurrence();
        ocurrence.setStatus(OcurrenceStatus.IN_TRANSIT);
        ocurrence.setOrder(order);

        when(orderRepository.findByTrackingCode("T123")).thenReturn(Optional.of(order));
        when(ocurrenceRepository.findTopByOrderOrderByOcurrenceTimeDesc(order)).thenReturn(Optional.empty());
        when(ocurrenceMapper.toEntity(dto, order)).thenReturn(ocurrence);
        when(ocurrenceRepository.save(ocurrence)).thenReturn(ocurrence);

        Ocurrence result = ocurrenceService.registerOcurrence(dto);

        assertEquals(OcurrenceStatus.IN_TRANSIT, result.getStatus());
        verify(ocurrenceRepository).save(ocurrence);
    }

    @Test
    void throwErrorWhenOrderNotFound() {
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("T999");

        when(orderRepository.findByTrackingCode("T999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ocurrenceService.registerOcurrence(dto));
    }

    @Test
    void throwErrorWhenOrderIsDelivered() {
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("T123");
        dto.setStatus(OcurrenceStatus.IN_TRANSIT);

        Order order = new Order();
        Ocurrence last = new Ocurrence();
        last.setStatus(OcurrenceStatus.DELIVERED);

        when(orderRepository.findByTrackingCode("T123")).thenReturn(Optional.of(order));
        when(ocurrenceRepository.findTopByOrderOrderByOcurrenceTimeDesc(order)).thenReturn(Optional.of(last));

        assertThrows(IllegalStateException.class, () -> ocurrenceService.registerOcurrence(dto));
    }

    @Test
    void throwErrorWhenInvalidStatusAfterNotDelivered() {
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("T123");
        dto.setStatus(OcurrenceStatus.IN_TRANSIT);

        Order order = new Order();
        Ocurrence last = new Ocurrence();
        last.setStatus(OcurrenceStatus.NOT_DELIVERED);

        when(orderRepository.findByTrackingCode("T123")).thenReturn(Optional.of(order));
        when(ocurrenceRepository.findTopByOrderOrderByOcurrenceTimeDesc(order)).thenReturn(Optional.of(last));

        assertThrows(IllegalStateException.class, () -> ocurrenceService.registerOcurrence(dto));
    }

    @Test
    void returnTrackingStatusWithSuccess() {
        String code = "T123";
        Order order = new Order();
        order.setTrackingCode(code);
        order.setClientName("Maria");
        order.setAddress("Rua A");

        Ocurrence oc1 = new Ocurrence();
        oc1.setStatus(OcurrenceStatus.IN_TRANSIT);
        oc1.setOcurrenceTime(LocalDateTime.now().minusHours(2));

        Ocurrence oc2 = new Ocurrence();
        oc2.setStatus(OcurrenceStatus.OUT_FOR_DELIVERY);
        oc2.setOcurrenceTime(LocalDateTime.now());

        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(ocurrenceRepository.findByOrder_TrackingCodeOrderByOcurrenceTimeAsc(code))
                .thenReturn(List.of(oc1, oc2));

        TrackingStatusResponseDTO response = ocurrenceService.getTrackingStatus(code);

        assertEquals(code, response.getTrackingCode());
        assertEquals("OUT_FOR_DELIVERY", response.getCurrentStatus());
        assertEquals(2, response.getTimeline().size());
    }

    @Test
    void throwErrorWhenNoOrder() {
        when(orderRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> ocurrenceService.getTrackingStatus("T000"));
    }

    @Test
    void throwErrorWhenNoOcurrences() {
        String code = "T123";
        Order order = new Order();
        order.setTrackingCode(code);

        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(ocurrenceRepository.findByOrder_TrackingCodeOrderByOcurrenceTimeAsc(code))
                .thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> ocurrenceService.getTrackingStatus(code));
    }
}

