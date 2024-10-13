package com.spotride.spotride.model.vehiclephoto.dto.request;

import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create DTO model for {@link VehiclePhoto}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePhotoCreateRequestDto {
    @NotNull(message = "URL is required")
    private String url;
}
