package com.spotride.spotride.model.vehicle.dto.request;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO model for {@link Vehicle} updating.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateRequestDto {

    @NotNull(message = "ID is required")
    private Long id;

    private Long userId;

    private String brand;

    private String model;

    private String generation;

    private int productYear;

    private String bodyType;

    private String engineType;

    private int enginePower;

    private int engineDisplacement;

    private String vehiclePhotoUrl;

    private List<VehicleRecord> vehicleRecords;
}
