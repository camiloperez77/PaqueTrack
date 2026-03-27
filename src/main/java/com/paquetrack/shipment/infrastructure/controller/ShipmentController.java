package com.paquetrack.shipment.infrastructure.controller;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.CreateShipmentUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentByTrackingUseCase;  
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    
    private final CreateShipmentUseCase createShipmentUseCase;
    private final GetShipmentUseCase getShipmentUseCase;
    private final GetShipmentByTrackingUseCase getShipmentByTrackingUseCase;  
    
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment created = createShipmentUseCase.createShipment(shipment);
        return ResponseEntity
                .created(URI.create("/api/v1/shipments/" + created.getId()))
                .body(created);
    }
    
    // Consultar por ID interno
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipment(@PathVariable String id) {
        return getShipmentUseCase.getShipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 👈 NUEVO: Consultar por NÚMERO DE GUÍA (trackingId)
    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<Shipment> getShipmentByTrackingId(@PathVariable String trackingId) {
        return getShipmentByTrackingUseCase.getShipmentByTrackingId(trackingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}