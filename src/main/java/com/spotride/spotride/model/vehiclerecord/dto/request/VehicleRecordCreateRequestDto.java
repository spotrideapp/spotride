package com.spotride.spotride.model.vehiclerecord.dto.request;

import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request DTO for entity create.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRecordCreateRequestDto {

    private List<VehiclePhoto> vehiclePhotos;
    private String description;
}
