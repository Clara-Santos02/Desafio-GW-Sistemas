package com.example.desafio.controller;

import com.example.desafio.dto.RegisterOrderDTO;
import com.example.desafio.entity.Order;
import com.example.desafio.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody RegisterOrderDTO request) {
        Order created = orderService.registerOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
