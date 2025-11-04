package com.example.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TrackingStatusResponseDTO {
    private String trackingCode;
    private String clientName;
    private String address;
    private List<OcurrenceTimeLineDTO> timeline;
    private String currentStatus;
}
