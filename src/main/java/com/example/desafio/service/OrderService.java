package com.example.desafio.service;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import com.example.desafio.mapper.OrderMapper;
import com.example.desafio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order registerOrder(RegisterOrderDTO dto) {
        if (orderRepository.existsByTrackingCode(dto.getTrackingCode())) {
            throw new IllegalArgumentException("Tracking code already registered: " + dto.getTrackingCode());
        }

        Order order = orderMapper.toEntity(dto);

        return orderRepository.save(order);
    }
}
