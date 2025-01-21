package com.spotride.spotride.model.vehiclephoto;

import com.spotride.spotride.model.vehiclephoto.controller.VehiclePhotoController;
import com.spotride.spotride.model.vehiclephoto.dto.VehiclePhotoResponseDto;
import com.spotride.spotride.model.vehiclephoto.dto.request.VehiclePhotoUpdateRequestDto;
import com.spotride.spotride.model.vehiclephoto.service.VehiclePhotoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = VehiclePhotoController.class)
@ExtendWith(SpringExtension.class)
class VehiclePhotoControllerTest {

    @MockBean
    private VehiclePhotoService mockVehiclePhotoService;

    @Autowired
    private VehiclePhotoController vehiclePhotoController;

    @Test
    void testGetAllVehiclePhotos() {
        var vehiclePhotoDto = VehiclePhotoResponseDto.builder()
                .id(1L)
                .url("http://test.url")
                .build();

        when(mockVehiclePhotoService.getAll()).thenReturn(List.of(vehiclePhotoDto));

        var result = vehiclePhotoController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mockVehiclePhotoService, times(1)).getAll();
    }

    @Test
    void testGetVehiclePhotoById() {
        var vehiclePhotoDto = VehiclePhotoResponseDto.builder()
                .id(1L)
                .url("http://test.url")
                .build();

        when(mockVehiclePhotoService.getById(1L)).thenReturn(vehiclePhotoDto);

        var response = vehiclePhotoController.getById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("http://test.url", Objects.requireNonNull(response.getBody()).getUrl());
        verify(mockVehiclePhotoService, times(1)).getById(1L);
    }

    @Test
    void testUpdateVehiclePhoto() {
        var vehiclePhotoUpdateRequestDto = VehiclePhotoUpdateRequestDto.builder()
                .id(null)
                .url("http://test.url")
                .build();

        var updatedVehiclePhotoDto = VehiclePhotoResponseDto.builder()
                .id(1L)
                .url("http://updated.url")
                .build();

        when(mockVehiclePhotoService.update(1L, vehiclePhotoUpdateRequestDto)).thenReturn(updatedVehiclePhotoDto);

        var response = vehiclePhotoController.update(1L, vehiclePhotoUpdateRequestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("http://updated.url", Objects.requireNonNull(response.getBody()).getUrl());
        verify(mockVehiclePhotoService, times(1)).update(1L, vehiclePhotoUpdateRequestDto);
    }

    @Test
    void testDeleteVehiclePhoto() {
        doNothing().when(mockVehiclePhotoService).delete(1L);

        var response = vehiclePhotoController.delete(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(mockVehiclePhotoService, times(1)).delete(1L);
    }
}
