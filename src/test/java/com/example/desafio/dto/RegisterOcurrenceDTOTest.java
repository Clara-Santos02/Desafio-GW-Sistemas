package com.example.desafio.dto;

import com.example.desafio.entity.enums.OcurrenceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterOcurrenceDTOTest {

    @Test
    void shouldCreateAndAccessFields() {
        RegisterOcurrenceDTO dto = new RegisterOcurrenceDTO();
        dto.setTrackingCode("ABC");
        dto.setStatus(OcurrenceStatus.IN_TRANSIT);

        assertEquals("ABC", dto.getTrackingCode());
        assertEquals(OcurrenceStatus.IN_TRANSIT, dto.getStatus());
    }
}
