package com.spotride.spotride.model.vehicle.service;

import com.spotride.spotride.model.user.service.UserService;
import com.spotride.spotride.model.vehicle.VehicleMapper;
import com.spotride.spotride.model.vehicle.dto.VehicleResponseDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleCreateRequestDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleUpdateRequestDto;
import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehicle.repository.VehicleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for {@link Vehicle}.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleService {

    VehicleRepository vehicleRepository;
    VehicleMapper vehicleMapper;
    UserService userService;

    /**
     * Returns all entities.
     *
     * @return all entities
     */
    public List<VehicleResponseDto> getAll() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toDto)
                .toList();
    }

    /**
     * Returns entity by their id.
     *
     * @param id id
     * @return {@link VehicleResponseDto} by putted id
     */
    public VehicleResponseDto getById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDto)
                .orElse(null);
    }

    /**
     * Creates entity.
     *
     * @param vehicleCreateRequestDto entity for creating {@link  VehicleResponseDto}
     * @return created {@link VehicleResponseDto}
     */
    public VehicleResponseDto create(VehicleCreateRequestDto vehicleCreateRequestDto) {
        var vehicle = vehicleMapper.toEntity(vehicleCreateRequestDto);
        var userId = vehicleCreateRequestDto.getUserId();
        var userById = userService.getUserEntityById(userId);

        vehicle.setUser(userById.get());

        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    /**
     * Updates entity by id.
     *
     * @param id entity id
     * @param vehicleUpdateRequestDto entity for updating
     * @return updated {@link VehicleResponseDto}
     */
    public VehicleResponseDto update(Long id, VehicleUpdateRequestDto vehicleUpdateRequestDto) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicleMapper.updateEntityFromDto(vehicleUpdateRequestDto, vehicle);
                    return vehicleMapper.toDto(vehicleRepository.save(vehicle));
                })
                .orElse(null);
    }

    /**
     * Delete entity by id.
     *
     * @param id entity id
     */
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
}
