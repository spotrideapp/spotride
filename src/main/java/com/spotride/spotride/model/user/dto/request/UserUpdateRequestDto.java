package com.spotride.spotride.model.user.dto.request;

import com.spotride.spotride.model.vehicle.model.Vehicle;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;

/**
 * User request DTO to update model.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private LocalDate birthDate;

    private String city;

    private List<Vehicle> vehicles;
}
