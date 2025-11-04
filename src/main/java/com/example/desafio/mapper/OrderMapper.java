package com.example.desafio.mapper;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(RegisterOrderDTO dto) {
        Order order = new Order();
        order.setTrackingCode(dto.getTrackingCode());
        order.setClientName(dto.getClientName());
        order.setAddress(dto.getAddress());
        return order;
    }
}
