package com.paquetrack.shipment.infrastructure.config;

import com.paquetrack.shipment.application.service.CreateShipmentService;
import com.paquetrack.shipment.application.service.GetShipmentService;
import com.paquetrack.shipment.application.service.GetShipmentByTrackingService;
import com.paquetrack.shipment.domain.port.in.CreateShipmentUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentUseCase;
import com.paquetrack.shipment.domain.port.in.GetShipmentByTrackingUseCase;
import com.paquetrack.shipment.domain.port.out.ShipmentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CreateShipmentUseCase createShipmentUseCase(
            ShipmentRepositoryPort repo) {
        return new CreateShipmentService(repo);
    }

    @Bean
    public GetShipmentUseCase getShipmentUseCase(
            ShipmentRepositoryPort repo) {
        return new GetShipmentService(repo);
    }

    @Bean
    public GetShipmentByTrackingUseCase getShipmentByTrackingUseCase(
            ShipmentRepositoryPort repo) {
        return new GetShipmentByTrackingService(repo);
    }
}