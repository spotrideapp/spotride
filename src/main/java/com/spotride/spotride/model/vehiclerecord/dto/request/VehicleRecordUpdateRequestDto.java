package com.spotride.spotride.model.vehiclerecord.dto.request;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request DTO for entity update.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRecordUpdateRequestDto {

    @NotNull(message = "ID is required")
    private Long id;

    private Vehicle vehicle;

    @NotNull(message = "Photos is required")
    private List<VehiclePhoto> vehiclePhotos;

    private String description;
}
