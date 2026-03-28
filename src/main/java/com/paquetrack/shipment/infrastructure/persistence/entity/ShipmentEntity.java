package com.paquetrack.shipment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {

    @Id
    private String id;
    
    @Column(unique = true, nullable = false)
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