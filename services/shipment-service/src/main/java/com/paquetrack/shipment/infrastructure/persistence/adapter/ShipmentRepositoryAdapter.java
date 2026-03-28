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

    private final JpaShipmentRepository jpaShipmentRepository;

    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity entity = ShipmentEntity.builder()
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
        
        ShipmentEntity savedEntity = jpaShipmentRepository.save(entity);
        
        return Shipment.builder()
                .id(savedEntity.getId())
                .trackingId(savedEntity.getTrackingId())
                .status(savedEntity.getStatus())
                .senderName(savedEntity.getSenderName())
                .senderAddress(savedEntity.getSenderAddress())
                .senderCity(savedEntity.getSenderCity())
                .recipientName(savedEntity.getRecipientName())
                .recipientAddress(savedEntity.getRecipientAddress())
                .recipientCity(savedEntity.getRecipientCity())
                .weightKg(savedEntity.getWeightKg())
                .createdAt(savedEntity.getCreatedAt())
                .updatedAt(savedEntity.getUpdatedAt())
                .build();
    }

    @Override
    public Optional<Shipment> findById(String id) {
        return jpaShipmentRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Shipment> findByTrackingId(String trackingId) {
        return jpaShipmentRepository.findByTrackingId(trackingId)
                .map(this::toDomain);
    }

    @Override
    public long count() {
        return jpaShipmentRepository.count();
    }

    private Shipment toDomain(ShipmentEntity entity) {
        return Shipment.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .status(entity.getStatus())
                .senderName(entity.getSenderName())
                .senderAddress(entity.getSenderAddress())
                .senderCity(entity.getSenderCity())
                .recipientName(entity.getRecipientName())
                .recipientAddress(entity.getRecipientAddress())
                .recipientCity(entity.getRecipientCity())
                .weightKg(entity.getWeightKg())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}