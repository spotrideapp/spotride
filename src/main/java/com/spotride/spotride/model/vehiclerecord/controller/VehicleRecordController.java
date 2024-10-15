package com.spotride.spotride.model.vehiclerecord.controller;

import com.spotride.spotride.model.vehiclerecord.dto.VehicleRecordResponseDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordCreateRequestDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordUpdateRequestDto;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import com.spotride.spotride.model.vehiclerecord.service.VehicleRecordService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
 * Controller for {@link VehicleRecord}.
 */
@Slf4j
@RestController
@RequestMapping("/vehicle_record")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleRecordController {

    VehicleRecordService vehicleRecordService;

    /**
     * Returns all entities.
     *
     * @return all entities
     */
    @GetMapping
    public List<VehicleRecordResponseDto> getAll() {
        log.info("Get all Vehicle Records requested.");
        return vehicleRecordService.getAll();
    }

    /**
     * Returns entity by their id.
     *
     * @param id id
     * @return {@link VehicleRecordResponseDto} by putted id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleRecordResponseDto> getById(@PathVariable long id) {
        log.info("Get Vehicle Record by id {} requested.", id);
        var recordResponseDto = vehicleRecordService.getById(id);

        return nonNull(recordResponseDto) ? ResponseEntity.ok(recordResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Creates entity.
     *
     * @param vehicleRecordCreateRequestDto entity for creating {@link  VehicleRecordResponseDto}
     * @return created {@link VehicleRecordResponseDto}
     */
    @PostMapping
    public ResponseEntity<VehicleRecordResponseDto> create(
            @RequestBody @Valid VehicleRecordCreateRequestDto vehicleRecordCreateRequestDto
    ) {
        log.info("Create Vehicle Record requested.");
        var createdRecord = vehicleRecordService.create(vehicleRecordCreateRequestDto);

        return ResponseEntity.ok(createdRecord);
    }

    /**
     * Updates entity by id.
     *
     * @param id entity id
     * @param vehicleRecordUpdateRequestDto entity for updating
     * @return updated {@link VehicleRecordResponseDto}
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleRecordResponseDto> update(
            @PathVariable long id,
            @RequestBody @Valid VehicleRecordUpdateRequestDto vehicleRecordUpdateRequestDto
    ) {
        log.info("Update Vehicle Record by id {} requested.", id);
        var vehicleRecordResponseDto = vehicleRecordService.update(id, vehicleRecordUpdateRequestDto);

        return nonNull(vehicleRecordResponseDto) ? ResponseEntity.ok(vehicleRecordResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Delete entity by id.
     *
     * @param id entity id
     * @return no content response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Delete Vehicle Record by id {} requested.", id);
        vehicleRecordService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
