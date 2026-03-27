package com.paquetrack.shipment.application.service;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.CreateShipmentUseCase;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateShipmentService implements CreateShipmentUseCase {
    
    private final ShipmentRepositoryPort repository;
    
    @Override
    @Transactional
    public Shipment createShipment(Shipment shipment) {
        // Generar ID único
        shipment.setId(java.util.UUID.randomUUID().toString());
        
        // Generar tracking ID
        shipment.setTrackingId(shipment.generateTrackingId());
        
        // Marcar como creado
        shipment.markAsCreated();
        
        // Persistir
        return repository.save(shipment);
    }
}

