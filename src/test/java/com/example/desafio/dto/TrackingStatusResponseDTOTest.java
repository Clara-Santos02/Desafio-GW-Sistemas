package com.example.desafio.dto;

import com.example.desafio.entity.enums.OcurrenceStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackingStatusResponseDTOTest {

    @Test
    void shouldStoreValuesCorrectly() {
        OcurrenceTimeLineDTO timelineItem = new OcurrenceTimeLineDTO(OcurrenceStatus.OUT_FOR_DELIVERY, LocalDateTime.now());
        List<OcurrenceTimeLineDTO> timeline = List.of(timelineItem);

        TrackingStatusResponseDTO dto = new TrackingStatusResponseDTO(
                "TRACK123",
                "Maria",
                "Rua B, 10",
                timeline,
                "OUT_FOR_DELIVERY"
        );

        assertEquals("TRACK123", dto.getTrackingCode());
        assertEquals("Maria", dto.getClientName());
        assertEquals("Rua B, 10", dto.getAddress());
        assertEquals(timeline, dto.getTimeline());
        assertEquals("OUT_FOR_DELIVERY", dto.getCurrentStatus());
    }
}