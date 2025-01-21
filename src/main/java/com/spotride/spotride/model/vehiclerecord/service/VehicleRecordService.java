package com.spotride.spotride.model.vehiclerecord.service;

import com.spotride.spotride.model.vehicle.repository.VehicleRepository;
import com.spotride.spotride.model.vehiclerecord.mapper.VehicleRecordMapper;
import com.spotride.spotride.model.vehiclerecord.dto.VehicleRecordResponseDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordCreateRequestDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordUpdateRequestDto;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import com.spotride.spotride.model.vehiclerecord.repository.VehicleRecordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for {@link VehicleRecord}.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleRecordService {

    VehicleRecordRepository vehicleRecordRepository;
    VehicleRecordMapper vehicleRecordMapper;
    VehicleRepository vehicleRepository;

    /**
     * Returns all entities.
     *
     * @return all entities
     */
    public List<VehicleRecordResponseDto> getAll() {
        return vehicleRecordRepository.findAll().stream()
                .map(vehicleRecordMapper::toDto)
                .toList();
    }

    /**
     * Returns entity by their id.
     *
     * @param id id
     * @return {@link VehicleRecordResponseDto} by putted id
     */
    public VehicleRecordResponseDto getById(Long id) {
        return vehicleRecordRepository.findById(id)
                .map(vehicleRecordMapper::toDto)
                .orElse(null);
    }

    /**
     * Creates entity.
     *
     * @param vehicleRecordCreateRequestDto entity for creating {@link  VehicleRecordResponseDto}
     * @return created {@link VehicleRecordResponseDto}
     */
    public VehicleRecordResponseDto create(VehicleRecordCreateRequestDto vehicleRecordCreateRequestDto) {
        var vehicleRecord = vehicleRecordMapper.toEntity(vehicleRecordCreateRequestDto);

        if (vehicleRecord.getVehiclePhotos() != null) {
            for (var vehiclePhoto : vehicleRecord.getVehiclePhotos()) {
                vehiclePhoto.setVehicleRecord(vehicleRecord);
            }
        }

        var vehicleOpt = vehicleRepository.findById(vehicleRecordCreateRequestDto.getVehicleId());
        vehicleOpt.ifPresentOrElse(
                vehicleRecord::setVehicle,
                () -> {
                    throw new RuntimeException(String.format("Vehicle record with id [%s] not found", vehicleRecord.getId()));
                }
        );

        return vehicleRecordMapper.toDto(vehicleRecordRepository.save(vehicleRecord));
    }

    /**
     * Updates entity by id.
     *
     * @param id entity id
     * @param vehicleRecordUpdateRequestDto entity for updating
     * @return updated {@link VehicleRecordResponseDto}
     */
    public VehicleRecordResponseDto update(Long id, VehicleRecordUpdateRequestDto vehicleRecordUpdateRequestDto) {
        return vehicleRecordRepository.findById(id)
                .map(vehicleRecord -> {
                    vehicleRecordMapper.updateEntityFromDto(vehicleRecordUpdateRequestDto, vehicleRecord);
                    return vehicleRecordMapper.toDto(vehicleRecordRepository.save(vehicleRecord));
                })
                .orElse(null);
    }

    /**
     * Delete entity by id.
     *
     * @param id entity id
     */
    public void delete(Long id) {
        vehicleRecordRepository.deleteById(id);
    }
}
