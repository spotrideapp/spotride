package com.spotride.spotride.model.vehicle.controller;

import com.spotride.spotride.model.vehicle.dto.VehicleResponseDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleCreateRequestDto;
import com.spotride.spotride.model.vehicle.dto.request.VehicleUpdateRequestDto;
import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehicle.service.VehicleService;
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
 * Controller for {@link Vehicle}.
 */
@Slf4j
@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleController {

    VehicleService vehicleService;

    /**
     * Returns all entities.
     *
     * @return all entities
     */
    @GetMapping
    public List<VehicleResponseDto> getAll() {
        log.info("Get all Vehicles requested.");
        return vehicleService.getAll();
    }

    /**
     * Returns entity by their id.
     *
     * @param id id
     * @return {@link VehicleResponseDto} by putted id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> getById(@PathVariable long id) {
        log.info("Get Vehicle by id {} requested.", id);
        var vehicleResponseDto = vehicleService.getById(id);

        return nonNull(vehicleResponseDto) ? ResponseEntity.ok(vehicleResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Creates entity.
     *
     * @param vehicleCreateRequestDto entity for creating {@link  VehicleResponseDto}
     * @return created {@link VehicleResponseDto}
     */
    @PostMapping
    public ResponseEntity<VehicleResponseDto> create(
            @RequestBody @Valid VehicleCreateRequestDto vehicleCreateRequestDto
    ) {
        log.info("Create Vehicle requested.");
        var created = vehicleService.create(vehicleCreateRequestDto);

        return ResponseEntity.ok(created);
    }

    /**
     * Updates entity by id.
     *
     * @param id entity id
     * @param vehicleUpdateRequestDto entity for updating
     * @return updated {@link VehicleResponseDto}
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> update(
            @PathVariable long id,
            @RequestBody @Valid VehicleUpdateRequestDto vehicleUpdateRequestDto
    ) {
        log.info("Update Vehicle by id {} requested.", id);
        var vehicleResponseDto = vehicleService.update(id, vehicleUpdateRequestDto);

        return nonNull(vehicleResponseDto) ? ResponseEntity.ok(vehicleResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Delete entity by id.
     *
     * @param id entity id
     * @return no content response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Delete Vehicle by id {} requested.", id);
        vehicleService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
