package com.example.desafio.entity.enums;

import lombok.Getter;

@Getter
public enum OcurrenceStatus {
    OUT_FOR_DELIVERY("Out for delivery"),
    IN_TRANSIT("In transit"),
    NOT_DELIVERED("Not Delivered"),
    DELIVERED("Delivered");

    private final String status;

    OcurrenceStatus(String status) {this.status = status; }

    @Override
    public String toString() { return this.status; }
}
