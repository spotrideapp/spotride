package com.spotride.spotride.user.service;

import com.spotride.spotride.user.UserMapper;
import com.spotride.spotride.user.dto.request.UserCreateRequestDto;
import com.spotride.spotride.user.dto.UserResponseDto;
import com.spotride.spotride.user.dto.request.UserUpdateRequestDto;
import com.spotride.spotride.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
