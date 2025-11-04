package com.example.desafio.service;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import com.example.desafio.mapper.OrderMapper;
import com.example.desafio.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldRegisterWithSuccess() {
        RegisterOrderDTO dto = new RegisterOrderDTO();
        dto.setTrackingCode("123");
        dto.setClientName("Maria");
        dto.setAddress("Rua A");

        Order order = new Order();
        order.setTrackingCode("123");

        when(orderRepository.existsByTrackingCode("123")).thenReturn(false);
        when(orderMapper.toEntity(dto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.registerOrder(dto);

        assertEquals("123", result.getTrackingCode());
        verify(orderRepository).save(order);
    }

    @Test
    void throwErrorWhenTrackingCodeExists() {
        RegisterOrderDTO dto = new RegisterOrderDTO();
        dto.setTrackingCode("ABC123");

        when(orderRepository.existsByTrackingCode("ABC123")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> orderService.registerOrder(dto));
        verify(orderRepository, never()).save(any());
    }
}

