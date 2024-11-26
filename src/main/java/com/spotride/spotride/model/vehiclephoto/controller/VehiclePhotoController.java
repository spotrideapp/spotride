package com.spotride.spotride.model.vehiclephoto.controller;

import com.spotride.spotride.model.vehiclephoto.VehiclePhotoFacade;
import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/vehicle_photo")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class VehiclePhotoController {

    VehiclePhotoFacade vehiclePhotoFacade;

    /**
     * Get all {@link VehiclePhotoResponseDto}.
     *
     * @return all {@link VehiclePhotoResponseDto}
     */
    @GetMapping
    public List<VehiclePhotoResponseDto> getAll() {
        log.info("Get all Vehicle Photo requested.");
        return vehiclePhotoFacade.getAll();
    }

    /**
     * get {@link VehiclePhotoResponseDto} by their id.
     *
     * @param id {@link VehiclePhotoResponseDto} id
     * @return {@link VehiclePhotoResponseDto} by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehiclePhotoResponseDto> getById(@PathVariable long id) {
        log.info("Get Vehicle Photo by id {} requested.", id);
        var userResponseDto = vehiclePhotoFacade.getById(id);

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
        log.info("Create Vehicle Photo requested.");
        var createdPhoto = vehiclePhotoFacade.create(vehiclePhotoCreateRequestDto);

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
        log.info("Update Vehicle Photo by id {} requested.", id);
        var vehiclePhotoResponseDto = vehiclePhotoFacade.update(id, vehiclePhotoUpdateRequestDto);

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
        log.info("Delete Vehicle Photo by id {} requested.", id);
        vehiclePhotoFacade.delete(id);

        return ResponseEntity.noContent().build();
    }
}
