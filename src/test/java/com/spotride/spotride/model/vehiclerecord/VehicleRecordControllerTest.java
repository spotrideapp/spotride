package com.spotride.spotride.model.vehiclerecord;

import com.spotride.spotride.model.vehiclerecord.controller.VehicleRecordController;
import com.spotride.spotride.model.vehiclerecord.dto.VehicleRecordResponseDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordCreateRequestDto;
import com.spotride.spotride.model.vehiclerecord.dto.request.VehicleRecordUpdateRequestDto;
import com.spotride.spotride.model.vehiclerecord.service.VehicleRecordService;
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

@ContextConfiguration(classes = VehicleRecordController.class)
@ExtendWith(SpringExtension.class)
class VehicleRecordControllerTest {

    @MockBean
    private VehicleRecordService mockVehicleRecordService;

    @Autowired
    private VehicleRecordController vehicleRecordController;

    @Test
    void testGetAllVehicleRecords() {
        var vehicleRecordDto = VehicleRecordResponseDto.builder()
                .id(1L)
                .description("Test description")
                .build();

        when(mockVehicleRecordService.getAll()).thenReturn(List.of(vehicleRecordDto));

        var result = vehicleRecordController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test description", result.getFirst().getDescription());
        verify(mockVehicleRecordService, times(1)).getAll();
    }

    @Test
    void testGetVehicleRecordById() {
        var vehicleRecordDto = VehicleRecordResponseDto.builder()
                .id(1L)
                .description("Test description")
                .build();

        when(mockVehicleRecordService.getById(1L)).thenReturn(vehicleRecordDto);

        var response = vehicleRecordController.getById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Test description", Objects.requireNonNull(response.getBody()).getDescription());
        verify(mockVehicleRecordService, times(1)).getById(1L);
    }

    @Test
    void testCreateVehicleRecord() {
        var vehicleRecordCreateRequestDto = VehicleRecordCreateRequestDto.builder()
                .description("New record description")
                .build();

        var createdVehicleRecordDto = VehicleRecordResponseDto.builder()
                .id(1L)
                .description("New record description")
                .build();

        when(mockVehicleRecordService.create(vehicleRecordCreateRequestDto)).thenReturn(createdVehicleRecordDto);

        var response = vehicleRecordController.create(vehicleRecordCreateRequestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("New record description", Objects.requireNonNull(response.getBody()).getDescription());
        verify(mockVehicleRecordService, times(1)).create(vehicleRecordCreateRequestDto);
    }

    @Test
    void testUpdateVehicleRecord() {
        var vehicleRecordUpdateRequestDto = VehicleRecordUpdateRequestDto.builder()
                .description("Updated description")
                .build();

        var updatedVehicleRecordDto = VehicleRecordResponseDto.builder()
                .id(1L)
                .description("Updated description")
                .build();

        when(mockVehicleRecordService.update(1L, vehicleRecordUpdateRequestDto)).thenReturn(updatedVehicleRecordDto);

        var response = vehicleRecordController.update(1L, vehicleRecordUpdateRequestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Updated description", Objects.requireNonNull(response.getBody()).getDescription());
        verify(mockVehicleRecordService, times(1)).update(1L, vehicleRecordUpdateRequestDto);
    }

    @Test
    void testDeleteVehicleRecord() {
        doNothing().when(mockVehicleRecordService).delete(1L);

        var response = vehicleRecordController.delete(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(mockVehicleRecordService, times(1)).delete(1L);
    }
}
