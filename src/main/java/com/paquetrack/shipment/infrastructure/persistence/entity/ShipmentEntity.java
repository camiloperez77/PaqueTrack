package com.paquetrack.shipment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
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
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private String senderName;
    
    private String senderAddress;
    
    @Column(nullable = false)
    private String recipientName;
    
    private String recipientAddress;
    
    private Double weightKg;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
