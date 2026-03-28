package com.paquetrack.shipment.infrastructure.controller;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.CreateShipmentUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentByTrackingUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentUseCase;
import com.paquetrack.shipment.infrastructure.dto.ShipmentRequestDTO;
import com.paquetrack.shipment.infrastructure.dto.ShipmentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final CreateShipmentUseCase createShipmentUseCase;
    private final GetShipmentUseCase getShipmentUseCase;
    private final GetShipmentByTrackingUseCase getShipmentByTrackingUseCase;

    @PostMapping
    public ResponseEntity<ShipmentResponseDTO> createShipment(
            @Valid @RequestBody ShipmentRequestDTO requestDTO) {
        
        // Convertir DTO a entidad de dominio
        Shipment shipment = Shipment.builder()
                .senderName(requestDTO.getSenderName())
                .senderAddress(requestDTO.getSenderAddress())
                .senderCity(requestDTO.getSenderCity())
                .recipientName(requestDTO.getRecipientName())
                .recipientAddress(requestDTO.getRecipientAddress())
                .recipientCity(requestDTO.getRecipientCity())
                .weightKg(requestDTO.getWeightKg())
                .build();
        
        Shipment created = createShipmentUseCase.createShipment(shipment);
        
        // Convertir entidad de dominio a DTO de respuesta
        ShipmentResponseDTO response = toResponseDTO(created);
        
        return ResponseEntity
                .created(URI.create("/api/shipments/" + created.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponseDTO> getShipment(@PathVariable String id) {
        Optional<Shipment> shipment = getShipmentUseCase.getShipment(id);
        return shipment.map(this::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentByTrackingId(
            @PathVariable String trackingId) {
        Optional<Shipment> shipment = getShipmentByTrackingUseCase
                .getShipmentByTrackingId(trackingId);
        return shipment.map(this::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método privado para convertir de dominio a DTO
    private ShipmentResponseDTO toResponseDTO(Shipment shipment) {
        return ShipmentResponseDTO.builder()
                .id(shipment.getId())
                .trackingId(shipment.getTrackingId())
                .status(shipment.getStatus())
                .senderName(shipment.getSenderName())
                .senderAddress(shipment.getSenderAddress())
                .senderCity(shipment.getSenderCity())
                .recipientName(shipment.getRecipientName())
                .recipientAddress(shipment.getRecipientAddress())
                .recipientCity(shipment.getRecipientCity())
                .weightKg(shipment.getWeightKg())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }
}