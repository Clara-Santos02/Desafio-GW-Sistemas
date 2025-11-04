package com.example.desafio.mapper;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderMapperTest {

    private final OrderMapper mapper = new OrderMapper();

    @Test
    void shouldMapDtoToEntityCorrectly() {
        // given
        RegisterOrderDTO dto = new RegisterOrderDTO();
        dto.setTrackingCode("XYZ789");
        dto.setClientName("Maria");
        dto.setAddress("Rua das Flores, 123");

        // when
        Order order = mapper.toEntity(dto);

        // then
        assertNotNull(order);
        assertEquals("XYZ789", order.getTrackingCode());
        assertEquals("Maria", order.getClientName());
        assertEquals("Rua das Flores, 123", order.getAddress());
    }
}
