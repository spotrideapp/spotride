package com.spotride.spotride.authentication.controller;

import com.spotride.spotride.authentication.dto.JwtAuthenticationRequestDto;
import com.spotride.spotride.authentication.dto.JwtAuthenticationResponseDto;
import com.spotride.spotride.authentication.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for User Authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    /**
     * Register new User.
     *
     * @param request request for registration
     * @return {@link ResponseEntity for register user}
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody JwtAuthenticationRequestDto request) {
        var token = authService.registerUser(request);
        return ResponseEntity.ok(new JwtAuthenticationResponseDto(token));
    }

    /**
     * Authenticate User.
     *
     * @param request request for authentication.
     *
     * @return {@link ResponseEntity} for authenticated user
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtAuthenticationRequestDto request) {
        var token = authService.authenticateUser(request);
        return ResponseEntity.ok(new JwtAuthenticationResponseDto(token));
    }
}
