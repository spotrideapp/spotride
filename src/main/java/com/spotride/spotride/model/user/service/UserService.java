package com.spotride.spotride.model.user.service;

import com.spotride.spotride.authentication.dto.JwtAuthenticationRequestDto;
import com.spotride.spotride.exception.EmailAlreadyTakenException;
import com.spotride.spotride.model.user.UserMapper;
import com.spotride.spotride.model.user.dto.UserResponseDto;
import com.spotride.spotride.model.user.dto.request.UserCreateRequestDto;
import com.spotride.spotride.model.user.dto.request.UserUpdateRequestDto;
import com.spotride.spotride.model.user.model.User;
import com.spotride.spotride.model.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class UserService {

    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Gets all users.
     *
     * @return list of {@link UserResponseDto}
     */
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Returns user by id.
     *
     * @param id user id
     * @return {@link UserResponseDto} by user id
     */
    public UserResponseDto getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    public Optional<User> getUserEntityById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Created user.
     *
     * @param userRequestDto {@link UserCreateRequestDto} for user
     * @return {@link UserResponseDto} for created user
     */
    public UserResponseDto create(UserCreateRequestDto userRequestDto) {
        var user = userMapper.toEntity(userRequestDto);

        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Update user by id.
     *
     * @param id user id
     * @param updatedUserDto user to be updated
     */
    public UserResponseDto update(Long id, UserUpdateRequestDto updatedUserDto) {
        return userRepository.findById(id)
                .map(user -> {
                    userMapper.updateEntityFromDto(updatedUserDto, user);
                    return userMapper.toDto(userRepository.save(user));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Registers a new user.
     *
     * @param authenticationRequest {@link JwtAuthenticationRequestDto} containing user registration details
     * @return {@link UserResponseDto} for the registered user
     * @throws RuntimeException if the email or username is already taken
     */
    public UserResponseDto registerUser(JwtAuthenticationRequestDto authenticationRequest) {
        var email = authenticationRequest.email();
        if (existsByEmail(email)) {
            log.error("User with email {} already exists", email);
            throw new EmailAlreadyTakenException(email);
        }

        var user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(authenticationRequest.password()))
                .build();


        log.debug("User {} successfully registered", user.getId());
        return userMapper.toDto(userRepository.save(user));
    }

    private boolean existsByEmail(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }
}
