package com.spotride.spotride.model.vehiclephoto.service;

import com.spotride.spotride.model.vehiclephoto.VehiclePhotoMapper;
import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import com.spotride.spotride.model.vehiclephoto.repository.VehiclePhotoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for {@link VehiclePhoto}.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class VehiclePhotoService {

    VehiclePhotoRepository vehiclePhotoRepository;
    VehiclePhotoMapper vehiclePhotoMapper;

    /**
     * Get all {@link VehiclePhotoResponseDto}.
     *
     * @return all {@link VehiclePhotoResponseDto} from {@link VehiclePhotoRepository}
     */
    public List<VehiclePhotoResponseDto> getAll() {
        return vehiclePhotoRepository.findAll().stream()
                .map(vehiclePhotoMapper::toDto)
                .toList();
    }

    /**
     * Get {@link VehiclePhotoResponseDto} by theis id.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     * @return {@link VehiclePhotoResponseDto} by provided id
     */
    public VehiclePhotoResponseDto getById(Long id) {
        return vehiclePhotoRepository.findById(id)
                .map(vehiclePhotoMapper::toDto)
                .orElse(null);
    }

    /**
     * Create {@link VehiclePhotoResponseDto}.
     *
     * @param vehiclePhotoCreateRequestDto DTO model for {@link VehiclePhotoResponseDto} creating
     * @return created {@link VehiclePhotoResponseDto}
     */
    public VehiclePhotoResponseDto create(VehiclePhotoCreateRequestDto vehiclePhotoCreateRequestDto) {
        var vehiclePhoto = vehiclePhotoMapper.toEntity(vehiclePhotoCreateRequestDto);

        return vehiclePhotoMapper.toDto(vehiclePhotoRepository.save(vehiclePhoto));
    }

    /**
     * Update {@link VehiclePhotoResponseDto}.
     *
     * @param vehiclePhotoUpdateRequestDto DTO model for {@link VehiclePhotoResponseDto} updating
     * @return created {@link VehiclePhotoResponseDto}
     */
    public VehiclePhotoResponseDto update(Long id, VehiclePhotoUpdateRequestDto vehiclePhotoUpdateRequestDto) {
        return vehiclePhotoRepository.findById(id)
                .map(vehiclePhoto -> {
                    vehiclePhotoMapper.updateEntityFromDto(vehiclePhotoUpdateRequestDto, vehiclePhoto);
                    return vehiclePhotoMapper.toDto(vehiclePhotoRepository.save(vehiclePhoto));
                })
                .orElse(null);
    }

    /**
     * Delete {@link VehiclePhotoResponseDto}.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     */
    public void delete(Long id) {
        vehiclePhotoRepository.deleteById(id);
    }
}
