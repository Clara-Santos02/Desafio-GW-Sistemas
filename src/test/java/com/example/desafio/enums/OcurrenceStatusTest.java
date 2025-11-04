package com.example.desafio.enums;

import com.example.desafio.entity.enums.OcurrenceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OcurrenceStatusTest {

    @Test
    void shouldReturnCorrectStatusString() {
        assertEquals("Out for delivery", OcurrenceStatus.OUT_FOR_DELIVERY.toString());
        assertEquals("In transit", OcurrenceStatus.IN_TRANSIT.toString());
        assertEquals("Not Delivered", OcurrenceStatus.NOT_DELIVERED.toString());
        assertEquals("Delivered", OcurrenceStatus.DELIVERED.toString());
    }

    @Test
    void shouldReturnEnumValuesProperly() {
        OcurrenceStatus[] values = OcurrenceStatus.values();
        assertEquals(4, values.length);
        assertNotNull(OcurrenceStatus.valueOf("DELIVERED"));
    }
}