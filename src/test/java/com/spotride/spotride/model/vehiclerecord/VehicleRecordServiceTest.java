package com.spotride.spotride.model.vehiclerecord;

import com.spotride.spotride.model.user.model.User;
import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehicle.repository.VehicleRepository;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordCreateRequestDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordUpdateRequestDto;
import com.spotride.spotride.model.vehiclerecord.mapper.VehicleMapperHelper;
import com.spotride.spotride.model.vehiclerecord.mapper.VehicleRecordMapper;
import com.spotride.spotride.model.vehiclerecord.mapper.VehicleRecordMapperImpl;
import com.spotride.spotride.model.vehiclerecord.model.VehicleRecord;
import com.spotride.spotride.model.vehiclerecord.repository.VehicleRecordRepository;
import com.spotride.spotride.model.vehiclerecord.service.VehicleRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VehicleRecordMapperImpl.class, VehicleRecordService.class})
@ExtendWith(SpringExtension.class)
class VehicleRecordServiceTest {

    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();

    @MockBean
    private VehicleRecordRepository mockVehicleRecordRepository;

    @MockBean
    private VehicleRepository mockVehicleRepository;

    @MockBean
    private VehicleMapperHelper mockVehicleMapperHelper;

    @Autowired
    private VehicleRecordMapper vehicleRecordMapper;

    @Autowired
    private VehicleRecordService vehicleRecordService;

    @Test
    void testGetAllVehicleRecords() {
        var vehicleRecord = VehicleRecord.builder()
                .id(1L)
                .description("Test description")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        when(mockVehicleRecordRepository.findAll()).thenReturn(List.of(vehicleRecord));

        var result = vehicleRecordService.getAll();

        assertEquals(1, result.size());
        assertEquals("Test description", result.getFirst().getDescription());
        verify(mockVehicleRecordRepository, times(1)).findAll();
    }

    @Test
    void testGetVehicleRecordById() {
        var vehicleRecord = VehicleRecord.builder()
                .id(1L)
                .description("Test description")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        when(mockVehicleRecordRepository.findById(1L)).thenReturn(Optional.of(vehicleRecord));

        var result = vehicleRecordService.getById(1L);

        assertNotNull(result);
        assertEquals("Test description", result.getDescription());
        verify(mockVehicleRecordRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateVehicleRecord() {
        var user = User.builder()
                .id(1L)
                .username("john")
                .password("password")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        var vehicle = Vehicle.builder()
                .id(1L)
                .brand("Opel")
                .model("Astra")
                .generation("G")
                .productYear(1999)
                .bodyType("Estate")
                .enginePower(75)
                .engineType("Disel")
                .engineDisplacement(1700)
                .vehiclePhotoUrl("https://example.com")
                .vehicleRecords(Collections.emptyList())
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        user.setVehicles(List.of(vehicle));

        var vehicleRecordCreateRequestDto = VehicleRecordCreateRequestDto.builder()
                .vehicleId(1L)
                .description("New description")
                .build();

        var savedVehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://test.url")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(DATE_TIME_NOW)
                .build();

        var savedRecord = VehicleRecord.builder()
                .id(1L)
                .vehicle(vehicle)
                .description("New description")
                .vehiclePhotos(List.of(savedVehiclePhoto))
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(DATE_TIME_NOW)
                .build();

        when(mockVehicleRecordRepository.save(any(VehicleRecord.class))).thenReturn(savedRecord);
        when(mockVehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));
        when(mockVehicleMapperHelper.mapIdByVehicle(vehicle)).thenReturn(1L);

        var vehicleRecord = vehicleRecordMapper.toEntity(vehicleRecordCreateRequestDto);
        vehicleRecord.setVehicle(vehicle);
        var created = vehicleRecordService.create(vehicleRecordCreateRequestDto);

        assertNotNull(created);
        assertEquals("New description", created.getDescription());
        assertEquals("http://test.url", created.getVehiclePhotos().getFirst().getUrl());
        assertEquals(vehicle.getId(), created.getVehicleId());
        verify(mockVehicleRecordRepository, times(1)).save(vehicleRecord);
    }

    @Test
    void testUpdateVehicleRecord() {
        var vehicleRecord = VehicleRecord.builder()
                .id(1L)
                .description("Old description")
                .vehiclePhotos(new ArrayList<>())
                .build();

        var vehiclePhotos = List.of(new VehiclePhoto(1L, "http://test.url", vehicleRecord, DATE_TIME_NOW, DATE_TIME_NOW));
        var vehicleRecordUpdateRequestDto = VehicleRecordUpdateRequestDto.builder()
                .description("Updated description")
                .vehiclePhotos(vehiclePhotos)
                .build();

        var updatedVehicleRecord = VehicleRecord.builder()
                .id(1L)
                .description("Updated description")
                .vehiclePhotos(vehiclePhotos)
                .build();

        when(mockVehicleRecordRepository.findById(1L)).thenReturn(Optional.of(vehicleRecord));
        when(mockVehicleRecordRepository.save(vehicleRecord)).thenReturn(updatedVehicleRecord);

        vehicleRecordMapper.updateEntityFromDto(vehicleRecordUpdateRequestDto, vehicleRecord);
        var result = vehicleRecordService.update(1L, vehicleRecordUpdateRequestDto);

        assertNotNull(result);
        assertEquals("Updated description", result.getDescription());
        assertEquals(updatedVehicleRecord.getVehiclePhotos(), result.getVehiclePhotos());
        verify(mockVehicleRecordRepository, times(1)).save(vehicleRecord);
    }

    @Test
    void testDeleteVehicleRecord() {
        doNothing().when(mockVehicleRecordRepository).deleteById(1L);

        vehicleRecordService.delete(1L);

        verify(mockVehicleRecordRepository, times(1)).deleteById(1L);
    }
}
