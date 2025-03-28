package com.spotride.spotride.model.user;

import com.spotride.spotride.model.user.controller.UserController;
import com.spotride.spotride.model.user.dto.UserResponseDto;
import com.spotride.spotride.model.user.dto.request.UserCreateRequestDto;
import com.spotride.spotride.model.user.dto.request.UserUpdateRequestDto;
import com.spotride.spotride.model.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link UserController}.
 */
@ContextConfiguration(classes = UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    private UserService mockUserService;

    @Autowired
    private UserController userController;

    @Test
    void testGetAllUsers() {
        var userDto = UserResponseDto.builder()
                .id(1L)
                .username("john")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();

        when(mockUserService.getAll()).thenReturn(List.of(userDto));

        var result = userController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mockUserService, times(1)).getAll();
    }

    @Test
    void testGetUserById() {
        var userDto = UserResponseDto.builder()
                .id(1L)
                .username("john")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();

        when(mockUserService.getById(1L)).thenReturn(userDto);

        var response = userController.getById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("john", Objects.requireNonNull(response.getBody()).getUsername());
        verify(mockUserService, times(1)).getById(1L);
    }

    @Test
    void testCreateUser() {
        var userCreateRequestDto = UserCreateRequestDto.builder()
                .username("john")
                .password("password")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();

        var createdUserDto = UserResponseDto.builder()
                .id(1L)
                .username("john")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();


        when(mockUserService.create(userCreateRequestDto)).thenReturn(createdUserDto);

        var response = userController.create(userCreateRequestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("john", Objects.requireNonNull(response.getBody()).getUsername());
        verify(mockUserService, times(1)).create(userCreateRequestDto);
    }

    @Test
    void testUpdateUser() {
        var userUpdateRequestDto = UserUpdateRequestDto.builder()
                .id(null)
                .username("john_updated")
                .password("password")
                .email("john_updated@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();

        var updatedUserDto = UserResponseDto.builder()
                .id(1L)
                .username("john_updated")
                .email("john_updated@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .build();

        when(mockUserService.update(1L, userUpdateRequestDto)).thenReturn(updatedUserDto);

        var response = userController.update(1L, userUpdateRequestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("john_updated", Objects.requireNonNull(response.getBody()).getUsername());
        verify(mockUserService, times(1)).update(1L, userUpdateRequestDto);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(mockUserService).delete(1L);

        var response = userController.delete(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(mockUserService, times(1)).delete(1L);
    }
}
