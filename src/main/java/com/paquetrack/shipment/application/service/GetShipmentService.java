package com.paquetrack.shipment.application.service;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.GetShipmentUseCase;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetShipmentService implements GetShipmentUseCase {
    
    private final ShipmentRepositoryPort repository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Shipment> getShipmentById(String id) {
        return repository.findById(id);
    }
}