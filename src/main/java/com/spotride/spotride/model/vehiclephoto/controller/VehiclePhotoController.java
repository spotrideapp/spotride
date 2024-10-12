package com.spotride.spotride.model.vehiclephoto.controller;

import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.service.VehiclePhotoService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Controller for {@link VehiclePhotoController}.
 */
@RestController
@RequestMapping("/vehicle_photo")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class VehiclePhotoController {

    VehiclePhotoService vehiclePhotoService;

    /**
     * Get all {@link VehiclePhotoResponseDto}.
     *
     * @return all {@link VehiclePhotoResponseDto}
     */
    @GetMapping
    public List<VehiclePhotoResponseDto> getAll() {
        return vehiclePhotoService.getAll();
    }

    /**
     * get {@link VehiclePhotoResponseDto} by their id.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     * @return {@link VehiclePhotoResponseDto} by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehiclePhotoResponseDto> getById(@PathVariable long id) {
        var userResponseDto = vehiclePhotoService.getById(id);

        return nonNull(userResponseDto) ? ResponseEntity.ok(userResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Create {@link VehiclePhotoResponseDto}.
     *
     * @param vehiclePhotoCreateRequestDto to {@link VehiclePhotoResponseDto} creating
     * @return created {@link VehiclePhotoResponseDto}
     */
    @PostMapping
    public ResponseEntity<VehiclePhotoResponseDto> create(@RequestBody @Valid VehiclePhotoCreateRequestDto vehiclePhotoCreateRequestDto) {
        var createdPhoto = vehiclePhotoService.create(vehiclePhotoCreateRequestDto);

        return ResponseEntity.ok(createdPhoto);
    }

    /**
     * Update {@link VehiclePhotoResponseDto}.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     * @param vehiclePhotoUpdateRequestDto to {@link VehiclePhotoResponseDto} updating
     * @return updated {@link VehiclePhotoResponseDto}
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehiclePhotoResponseDto> update(
            @PathVariable long id,
            @RequestBody @Valid VehiclePhotoUpdateRequestDto vehiclePhotoUpdateRequestDto
    ) {
        var vehiclePhotoResponseDto = vehiclePhotoService.update(id, vehiclePhotoUpdateRequestDto);

        return nonNull(vehiclePhotoResponseDto) ? ResponseEntity.ok(vehiclePhotoResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Delete {@link VehiclePhotoResponseDto}.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     * @return deleted {@link VehiclePhotoResponseDto}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        vehiclePhotoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
