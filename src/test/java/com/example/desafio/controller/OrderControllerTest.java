package com.example.desafio.controller;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import com.example.desafio.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrderWithSuccess() throws Exception {
        RegisterOrderDTO dto = new RegisterOrderDTO();
        dto.setTrackingCode("123");
        dto.setClientName("Maria");
        dto.setAddress("Rua A");

        Order order = new Order();
        order.setOrderId(1L);
        order.setTrackingCode("123");
        order.setClientName("Maria");
        order.setAddress("Rua A");

        when(orderService.registerOrder(any(RegisterOrderDTO.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "trackingCode": "123",
                              "clientName": "Maria",
                              "address": "Rua A"
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.trackingCode").value("123"))
                .andExpect(jsonPath("$.clientName").value("Maria"));
    }
}

