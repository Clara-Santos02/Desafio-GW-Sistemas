package com.example.desafio.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterOrderDTOTest {

    @Test
    void shouldCreateAndAccessFields() {
        RegisterOrderDTO dto = new RegisterOrderDTO();
        dto.setTrackingCode("123");
        dto.setClientName("Maria");
        dto.setAddress("Rua A, 99");

        assertEquals("123", dto.getTrackingCode());
        assertEquals("Maria", dto.getClientName());
        assertEquals("Rua A, 99", dto.getAddress());
    }
}
