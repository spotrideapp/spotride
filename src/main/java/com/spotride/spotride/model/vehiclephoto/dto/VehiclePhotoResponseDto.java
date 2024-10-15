package com.spotride.spotride.model.vehiclephoto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO model for {@link VehiclePhoto}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePhotoResponseDto {

    private Long id;

    private String url;

    @JsonIgnore
    private VehicleRecord vehicleRecord;
}
