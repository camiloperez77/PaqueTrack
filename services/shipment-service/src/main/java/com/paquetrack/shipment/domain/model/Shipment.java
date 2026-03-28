package com.paquetrack.shipment.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Shipment {
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
    
    // Método de dominio
    public void markAsCreated() {
        this.status = "CREATED";
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }
    
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }
}