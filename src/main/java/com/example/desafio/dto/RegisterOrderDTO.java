package com.example.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterOrderDTO {

    @NotBlank
    @Size(max = 20)
    private String trackingCode;

    @NotBlank
    @Size(max = 50)
    private String clientName;

    @NotBlank
    @Size(max = 50)
    private String address;
}
