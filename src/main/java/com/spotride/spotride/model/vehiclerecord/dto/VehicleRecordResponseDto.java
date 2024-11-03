package com.spotride.spotride.model.vehiclerecord.dto;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity response DTO.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRecordResponseDto {

    private Long id;

    private Vehicle vehicle;

    private List<VehiclePhoto> vehiclePhotos;

    private String description;
}
