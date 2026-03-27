// application/service/GetShipmentByTrackingService.java
package com.paquetrack.shipment.application.service;

import com.paquetrack.shipment.domain.model.Shipment;
import com.paquetrack.shipment.domain.port.in.GetShipmentByTrackingUseCase;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetShipmentByTrackingService implements GetShipmentByTrackingUseCase {
    
    private final ShipmentRepositoryPort repository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Shipment> getShipmentByTrackingId(String trackingId) {
        return repository.findByTrackingId(trackingId);
    }
}