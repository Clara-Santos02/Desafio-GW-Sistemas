package com.example.desafio.mapper;

import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OcurrenceMapper {

    public Ocurrence toEntity(RegisterOcurrenceDTO dto, Order order) {
        Ocurrence ocurrence = new Ocurrence();
        ocurrence.setOrder(order);
        ocurrence.setStatus(dto.getStatus());
        ocurrence.setOcurrenceTime(LocalDateTime.now());
        return ocurrence;
    }
}
