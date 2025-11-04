package com.example.desafio.controller;

import com.example.desafio.dto.OcurrenceTimeLineDTO;
import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.dto.TrackingStatusResponseDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.entity.enums.OcurrenceStatus;
import com.example.desafio.service.OcurrenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(OcurrenceController.class)
public class OcurrenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OcurrenceService ocurrenceService;

    @Test
    void registerOcurrenceWithSuccess() throws Exception {
        Ocurrence ocurrence = new Ocurrence();
        ocurrence.setOcurrenceId(1L);
        ocurrence.setStatus(OcurrenceStatus.IN_TRANSIT);

        when(ocurrenceService.registerOcurrence(any(RegisterOcurrenceDTO.class))).thenReturn(ocurrence);

        mockMvc.perform(post("/ocurrences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "trackingCode": "T123",
                              "status": "IN_TRANSIT"
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("IN_TRANSIT"));
    }

    @Test
    void returnTackingStatusWithSuccess() throws Exception {
        List<OcurrenceTimeLineDTO> timeline = List.of(
                new OcurrenceTimeLineDTO(OcurrenceStatus.IN_TRANSIT, LocalDateTime.now())
        );

        TrackingStatusResponseDTO response = new TrackingStatusResponseDTO(
                "T123", "Maria", "Rua A", timeline, "IN_TRANSIT"
        );

        when(ocurrenceService.getTrackingStatus("T123")).thenReturn(response);

        mockMvc.perform(get("/ocurrences/tracking")
                        .param("trackingCode", "T123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingCode").value("T123"))
                .andExpect(jsonPath("$.currentStatus").value("IN_TRANSIT"));
    }
}
