package com.spotride.spotride.model.vehiclephoto;

import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoCreateRequestDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.model.VehiclePhoto;
import com.spotride.spotride.model.vehiclephoto.repository.VehiclePhotoRepository;
import com.spotride.spotride.model.vehiclephoto.service.VehiclePhotoService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VehiclePhotoMapperImpl.class, VehiclePhotoService.class})
@ExtendWith(SpringExtension.class)
@SuppressFBWarnings(value = "SECHCP", justification = "Hardcoded password.")
class VehiclePhotoServiceTest {

    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();

    @MockBean
    private VehiclePhotoRepository mockVehiclePhotoRepository;

    @Autowired
    private VehiclePhotoMapper vehiclePhotoMapper;

    @Autowired
    private VehiclePhotoService vehiclePhotoService;

    @Test
    void testGetAllVehiclePhotos() {
        var vehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://test.url")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        when(mockVehiclePhotoRepository.findAll()).thenReturn(List.of(vehiclePhoto));

        var result = vehiclePhotoService.getAll();

        assertEquals(1, result.size());
        assertEquals("http://test.url", result.getFirst().getUrl());
        verify(mockVehiclePhotoRepository, times(1)).findAll();
    }

    @Test
    void testGetVehiclePhotoById() {
        var vehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://test.url")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(null)
                .build();

        when(mockVehiclePhotoRepository.findById(1L)).thenReturn(Optional.of(vehiclePhoto));

        var result = vehiclePhotoService.getById(1L);

        assertNotNull(result);
        assertEquals("http://test.url", result.getUrl());
        verify(mockVehiclePhotoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateVehiclePhoto() {
        var vehiclePhotoCreateRequestDto = VehiclePhotoCreateRequestDto.builder()
                .url("http://test.url")
                .build();

        var savedVehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://test.url")
                .createdAt(DATE_TIME_NOW)
                .modifiedAt(DATE_TIME_NOW)
                .build();

        when(mockVehiclePhotoRepository.save(any(VehiclePhoto.class))).thenReturn(savedVehiclePhoto);

        var vehiclePhoto = vehiclePhotoMapper.toEntity(vehiclePhotoCreateRequestDto);
        var created = vehiclePhotoService.create(vehiclePhotoCreateRequestDto);

        assertNotNull(created);
        assertEquals("http://test.url", created.getUrl());
        verify(mockVehiclePhotoRepository, times(1)).save(vehiclePhoto);
    }

    @Test
    void testUpdateVehiclePhoto() {
        var vehiclePhotoUpdateRequestDto = VehiclePhotoUpdateRequestDto.builder()
                .id(null)
                .url("http://test.url")
                .build();

        var vehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://test.url")
                .build();

        var updatedVehiclePhoto = VehiclePhoto.builder()
                .id(1L)
                .url("http://updated.url")
                .build();

        when(mockVehiclePhotoRepository.findById(1L)).thenReturn(Optional.of(vehiclePhoto));
        when(mockVehiclePhotoRepository.save(vehiclePhoto)).thenReturn(updatedVehiclePhoto);

        vehiclePhotoMapper.updateEntityFromDto(vehiclePhotoUpdateRequestDto, vehiclePhoto);
        var result = vehiclePhotoService.update(1L, vehiclePhotoUpdateRequestDto);

        assertNotNull(result);
        assertEquals("http://updated.url", result.getUrl());
        verify(mockVehiclePhotoRepository, times(1)).save(vehiclePhoto);
    }

    @Test
    void testDeleteVehiclePhoto() {
        doNothing().when(mockVehiclePhotoRepository).deleteById(1L);

        vehiclePhotoService.delete(1L);

        verify(mockVehiclePhotoRepository, times(1)).deleteById(1L);
    }
}
