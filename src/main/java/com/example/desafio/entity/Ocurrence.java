package com.example.desafio.entity;

import com.example.desafio.entity.enums.OcurrenceStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ocurrence")
public class Ocurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocurrenceId;

    @ManyToOne
    @JoinColumn(name = "tracking_code", nullable = false)
    @JsonBackReference
    private Order order;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private OcurrenceStatus status;

    @Column(name = "ocurrence_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime ocurrenceTime;
}
