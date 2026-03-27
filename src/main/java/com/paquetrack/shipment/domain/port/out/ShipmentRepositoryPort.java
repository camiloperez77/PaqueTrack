package com.paquetrack.shipment.domain.port.out;

import com.paquetrack.shipment.domain.model.Shipment;
import java.util.Optional;

public interface ShipmentRepositoryPort {
    Shipment save(Shipment shipment);
    Optional<Shipment> findById(String id);
    Optional<Shipment> findByTrackingId(String trackingId);
}