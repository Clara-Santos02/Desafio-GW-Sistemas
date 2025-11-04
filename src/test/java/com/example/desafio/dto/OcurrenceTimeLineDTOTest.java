package com.example.desafio.dto;

import com.example.desafio.entity.enums.OcurrenceStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OcurrenceTimeLineDTOTest {

    @Test
    void shouldStoreValuesCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        OcurrenceTimeLineDTO dto = new OcurrenceTimeLineDTO(OcurrenceStatus.DELIVERED, now);

        assertEquals(OcurrenceStatus.DELIVERED, dto.getStatus());
        assertEquals(now, dto.getOcurrenceTime());
    }
}
