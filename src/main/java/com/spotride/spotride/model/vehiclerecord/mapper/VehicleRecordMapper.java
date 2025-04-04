package com.spotride.spotride.model.vehiclerecord.mapper;

import com.spotride.spotride.model.vehiclerecord.dto.VehicleRecordResponseDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordCreateRequestDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordUpdateRequestDto;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * {@link VehicleRecord} mapper.
 */
@Mapper(componentModel = "spring", uses = VehicleMapperHelper.class)
public interface VehicleRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "vehicle", source = "vehicleId", qualifiedByName = "mapVehicleById")
    VehicleRecord toEntity(VehicleRecordCreateRequestDto vehicleRecordCreateRequestDto);

    @Mapping(target = "vehicleId", source = "vehicle", qualifiedByName = "mapIdByVehicle")
    VehicleRecordResponseDto toDto(VehicleRecord vehicleRecord);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    void updateEntityFromDto(VehicleRecordUpdateRequestDto vehicleRecordUpdateRequestDto, @MappingTarget VehicleRecord vehicleRecord);
}
