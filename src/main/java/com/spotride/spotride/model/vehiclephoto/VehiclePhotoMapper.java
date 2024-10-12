package com.spotride.spotride.model.vehiclephoto;

import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for {@link VehiclePhoto}.
 */
@Mapper(componentModel = "spring")
public interface VehiclePhotoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    VehiclePhoto toEntity(VehiclePhotoCreateRequestDto vehiclePhotoCreateRequestDto);

    VehiclePhotoResponseDto toDto(VehiclePhoto vehiclePhoto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    void updateEntityFromDto(VehiclePhotoUpdateRequestDto vehiclePhotoUpdateRequestDto, @MappingTarget VehiclePhoto vehiclePhoto);
}
