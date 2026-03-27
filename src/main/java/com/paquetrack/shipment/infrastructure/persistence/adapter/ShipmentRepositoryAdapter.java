package com.paquetrack.shipment.infrastructure.persistence.adapter;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import com.paquetrack.shipment.infrastructure.persistence.entity.ShipmentEntity;
import com.paquetrack.shipment.infrastructure.persistence.repository.JpaShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShipmentRepositoryAdapter implements ShipmentRepositoryPort {
    
    private final JpaShipmentRepository jpaRepository;
    
    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity entity = toEntity(shipment);
        ShipmentEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<Shipment> findById(String id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public Optional<Shipment> findByTrackingId(String trackingId) {  
        return jpaRepository.findByTrackingId(trackingId).map(this::toDomain);
    }
    
    private ShipmentEntity toEntity(Shipment shipment) {
        return ShipmentEntity.builder()
                .id(shipment.getId())
                .trackingId(shipment.getTrackingId())
                .status(shipment.getStatus())
                .senderName(shipment.getSenderName())
                .senderAddress(shipment.getSenderAddress())
                .recipientName(shipment.getRecipientName())
                .recipientAddress(shipment.getRecipientAddress())
                .weightKg(shipment.getWeightKg())
                .createdAt(shipment.getCreatedAt())
                .build();
    }
    
    private Shipment toDomain(ShipmentEntity entity) {
        return Shipment.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .status(entity.getStatus())
                .senderName(entity.getSenderName())
                .senderAddress(entity.getSenderAddress())
                .recipientName(entity.getRecipientName())
                .recipientAddress(entity.getRecipientAddress())
                .weightKg(entity.getWeightKg())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}