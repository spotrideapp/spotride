package com.spotride.spotride.user.controller;

import com.spotride.spotride.user.dto.request.UserCreateRequestDto;
import com.spotride.spotride.user.dto.UserResponseDto;
import com.spotride.spotride.user.dto.request.UserUpdateRequestDto;
import com.spotride.spotride.user.model.User;
import com.spotride.spotride.user.service.UserService;
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
 * Controller for {@link User}.
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class UserController {

    UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        log.info("Get all users requested.");
        return userService.getAllUsers();
    }

    /**
     * Returns user by id.
     *
     * @param id user id
     * @return {@link ResponseEntity} for user by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id) {
        log.info("Get user by id {} requested.", id);
        var userResponseDto = userService.getUserById(id);

        return nonNull(userResponseDto) ? ResponseEntity.ok(userResponseDto) : ResponseEntity.notFound().build();
    }

    /**
     * Creates user.
     *
     * @param userDto user dto model
     * @return {@link ResponseEntity} for created user
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateRequestDto userDto) {
        log.info("Create user requested.");
        var createdUser = userService.createUser(userDto);

        return ResponseEntity.ok(createdUser);
    }

    /**
     * Update user by id.
     *
     * @param id user id
     * @param userDto user dto model
     * @return {@link ResponseEntity} for updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable long id, @RequestBody @Valid UserUpdateRequestDto userDto) {
        log.info("Update user by id {} requested.", id);
        var updatedUser = userService.updateUser(id, userDto);

        return nonNull(updatedUser) ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    /**
     * Delete user by id.
     *
     * @param id user id
     * @return {@link ResponseEntity} for updated user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.info("Delete user by id {} requested.", id);
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
