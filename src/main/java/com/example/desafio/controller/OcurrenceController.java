package com.example.desafio.controller;

import com.example.desafio.dto.RegisterOcurrenceDTO;
import com.example.desafio.dto.TrackingStatusResponseDTO;
import com.example.desafio.entity.Ocurrence;
import com.example.desafio.service.OcurrenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocurrences")
@RequiredArgsConstructor
public class OcurrenceController {

    private final OcurrenceService ocurrenceService;

    @PostMapping
    public ResponseEntity<Ocurrence> registerOcurrence(@Valid @RequestBody RegisterOcurrenceDTO dto) {
        Ocurrence created = ocurrenceService.registerOcurrence(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/tracking")
    public ResponseEntity<TrackingStatusResponseDTO> getTrackingStatus(@RequestParam String trackingCode) {
        TrackingStatusResponseDTO response = ocurrenceService.getTrackingStatus(trackingCode);
        return ResponseEntity.ok(response);
    }
}
