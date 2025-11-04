package com.example.desafio.dto;

import com.example.desafio.entity.enums.OcurrenceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterOcurrenceDTO {

    @NotBlank
    private String trackingCode;

    @NotNull
    private OcurrenceStatus status;
}
