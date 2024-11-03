package com.spotride.spotride.model.vehicle;

import com.spotride.spotride.model.vehicle.dto.VehicleResponseDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleCreateRequestDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleUpdateRequestDto;
import com.spotride.spotride.model.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * {@link Vehicle} mapper.
 */
@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    Vehicle toEntity(VehicleCreateRequestDto vehicleCreateRequestDto);

    @Mapping(source = "user.id", target = "userId")
    VehicleResponseDto toDto(Vehicle vehicle);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    void updateEntityFromDto(VehicleUpdateRequestDto vehicleUpdateRequestDto, @MappingTarget Vehicle vehicle);
}
