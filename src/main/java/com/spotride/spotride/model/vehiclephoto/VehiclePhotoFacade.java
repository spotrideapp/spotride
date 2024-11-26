package com.spotride.spotride.model.vehiclephoto;

import com.spotride.spotride.model.common.ModelFacade;
import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import com.spotride.spotride.model.vehiclephoto.service.VehiclePhotoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link ModelFacade} implementation for {@link VehiclePhoto}.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class VehiclePhotoFacade
        implements ModelFacade<VehiclePhotoResponseDto, VehiclePhotoCreateRequestDto, VehiclePhotoUpdateRequestDto> {

    VehiclePhotoService vehiclePhotoService;

    @Override
    public List<VehiclePhotoResponseDto> getAll() {
        return vehiclePhotoService.getAll();
    }

    @Override
    public VehiclePhotoResponseDto getById(Long id) {
        return vehiclePhotoService.getById(id);
    }

    @Override
    public VehiclePhotoResponseDto create(VehiclePhotoCreateRequestDto createRequest) {
        return vehiclePhotoService.create(createRequest);
    }

    @Override
    public VehiclePhotoResponseDto update(Long id, VehiclePhotoUpdateRequestDto updateRequest) {
        return vehiclePhotoService.update(id, updateRequest);
    }

    @Override
    public void delete(Long id) {
        vehiclePhotoService.delete(id);
    }
}
