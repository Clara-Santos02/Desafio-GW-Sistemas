package com.example.desafio.dto;

import com.example.desafio.entity.enums.OcurrenceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OcurrenceTimeLineDTO {

    private OcurrenceStatus status;
    private LocalDateTime ocurrenceTime;
}
