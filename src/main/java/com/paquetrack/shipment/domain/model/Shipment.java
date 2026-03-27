package com.paquetrack.shipment.domain.model;


import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private String id;
    private String trackingId;
    private String status;
    private String senderName;
    private String senderAddress;
    private String recipientName;
    private String recipientAddress;
    private Double weightKg;
    private LocalDateTime createdAt;
}