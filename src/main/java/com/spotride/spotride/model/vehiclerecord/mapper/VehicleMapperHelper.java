package com.spotride.spotride.model.vehiclerecord.mapper;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehicle.repository.VehicleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * Helper for {@link VehicleRecordMapper}.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleMapperHelper {
    VehicleRepository vehicleRepository;

    /**
     * Helps to wire the vehicle by id.
     *
     * @param vehicleId vehicle id
     * @return Needed vehicle found by provided id
     */
    @Named("mapVehicleById")
    public Vehicle mapVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Vehicle with id %s not found", vehicleId)));
    }

    /**
     * Gets vehicle id.
     *
     * @param vehicle vehicle
     * @return vehicle id
     */
    @Named("mapIdByVehicle")
    public Long mapIdByVehicle(Vehicle vehicle) {
        return vehicle.getId();
    }
}
