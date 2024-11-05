package com.spotride.spotride.model.vehicle;

import com.spotride.spotride.model.user.model.User;
import com.spotride.spotride.model.user.service.UserService;
import com.spotride.spotride.model.vehicle.dto.request.VehicleCreateRequestDto;
import com.spotride.spotride.model.vehicle.model.Vehicle;
import com.spotride.spotride.model.vehicle.repository.VehicleRepository;
import com.spotride.spotride.model.vehicle.service.VehicleService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link VehicleService}.
 */
@ContextConfiguration(classes = {VehicleService.class, VehicleMapperImpl.class})
@ExtendWith(SpringExtension.class)
@SuppressFBWarnings(value = "SECHCP", justification = "Hardcoded password.")
class VehicleServiceTest {

    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();

    @MockBean
    private VehicleRepository mockVehicleRepository;

    @MockBean
    private UserService mockUserService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void testCreateVehicle() {
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

        var vehicleCreateRequestDto = VehicleCreateRequestDto.builder()
                .userId(1L)
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
                .build();

        var savedVehicle = Vehicle.builder()
                .id(1L)
                .user(user)
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

        when(mockUserService.getUserEntityById(1L)).thenReturn(Optional.of(user));
        when(mockVehicleRepository.save(any(Vehicle.class))).thenReturn(savedVehicle);

        var vehicle = vehicleMapper.toEntity(vehicleCreateRequestDto);
        var createdVehicle = vehicleService.create(vehicleCreateRequestDto);
        vehicle.setUser(user);

        assertNotNull(createdVehicle);
        assertEquals("Opel", createdVehicle.getBrand());
        assertEquals(1L, createdVehicle.getUserId());
        verify(mockVehicleRepository, times(1)).save(vehicle);
    }
}
