package com.example.desafio.mapper;

import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.Order;
import com.example.desafio.entity.enums.OcurrenceStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OcurrenceMapperTest {

    private final OcurrenceMapper mapper = new OcurrenceMapper();

    @Test
    void shouldMapDtoToEntityCorrectly() {
        // given
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("ABC123");
        dto.setStatus(OcurrenceStatus.IN_TRANSIT);

        Order order = new Order();
        order.setTrackingCode("ABC123");

        // when
        Ocurrence ocurrence = mapper.toEntity(dto, order);

        // then
        assertNotNull(ocurrence);
        assertEquals(order, ocurrence.getOrder());
        assertEquals(OcurrenceStatus.IN_TRANSIT, ocurrence.getStatus());
        assertNotNull(ocurrence.getOcurrenceTime());
        assertTrue(ocurrence.getOcurrenceTime().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}