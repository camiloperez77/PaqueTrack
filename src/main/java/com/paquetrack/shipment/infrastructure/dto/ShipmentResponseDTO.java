package com.paquetrack.shipment.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ShipmentResponseDTO {
    private String id;
    private String trackingId;
    private String status;
    private String senderName;
    private String senderAddress;
    private String senderCity;
    private String recipientName;
    private String recipientAddress;
    private String recipientCity;
    private Double weightKg;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}