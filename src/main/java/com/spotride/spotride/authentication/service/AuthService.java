package com.spotride.spotride.authentication.service;

import com.spotride.spotride.authentication.JwtTokenProvider;
import com.spotride.spotride.authentication.dto.JwtAuthenticationRequestDto;
import com.spotride.spotride.model.user.model.User;
import com.spotride.spotride.model.user.repository.UserRepository;
import com.spotride.spotride.model.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for User Authentication.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;

    UserService userService;

    PasswordEncoder passwordEncoder;

    JwtTokenProvider jwtTokenProvider;

    /**
     * Register User.
     *
     * @param request request for registration
     * @return generated token for registered User
     */
    public String registerUser(JwtAuthenticationRequestDto request) {
        var userResponseDto = userService.registerUser(request);
        return jwtTokenProvider.generateToken(userResponseDto.getEmail());
    }

    /**
     * Authenticate User.
     *
     * @param request request for authentication
     * @return token for authenticated User
     */
    public String authenticateUser(JwtAuthenticationRequestDto request) {
        var user = findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    private Optional<User> findByEmail(String email) {
        return userRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
