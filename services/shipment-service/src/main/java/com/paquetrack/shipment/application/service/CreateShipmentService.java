package com.paquetrack.shipment.application.service;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.CreateShipmentUseCase;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateShipmentService implements CreateShipmentUseCase {

    private final ShipmentRepositoryPort repository;

    @Override
    @Transactional
    public Shipment createShipment(Shipment shipment) {
        shipment.setId(UUID.randomUUID().toString());
        long next = repository.count() + 1;
        shipment.setTrackingId(String.format("LOG-2026-%05d", next));
        shipment.markAsCreated();
        return repository.save(shipment);
    }
}

