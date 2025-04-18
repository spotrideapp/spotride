package com.spotride.spotride.model.user.dto;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;

/**
 * User response DTO model.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate birthDate;

    private String city;

    private List<Vehicle> vehicles;
}
