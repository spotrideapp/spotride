package com.spotride.spotride.authentication;

import com.spotride.spotride.authentication.dto.JwtAuthenticationRequestDto;
import com.spotride.spotride.authentication.service.AuthService;
import com.spotride.spotride.model.user.dto.UserResponseDto;
import com.spotride.spotride.model.user.model.User;
import com.spotride.spotride.model.user.repository.UserRepository;
import com.spotride.spotride.model.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AuthService.class)
@ExtendWith(SpringExtension.class)
public class AuthServiceTest {

    @MockBean
    private UserRepository mockUserRepository;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private PasswordEncoder mockPasswordEncoder;

    @MockBean
    private JwtTokenProvider mockJwtTokenProvider;

    @Autowired
    AuthService authService;

    @Test
    void testRegisterUser() {
        var authenticationRequest = new JwtAuthenticationRequestDto("john@example.com", "password");
        var responseDto = UserResponseDto.builder().id(1L).email("john@example.com").build();

        when(mockUserService.registerUser(authenticationRequest)).thenReturn(responseDto);
        when(mockJwtTokenProvider.generateToken(responseDto.getEmail())).thenReturn("test_access_token");

        authService.registerUser(authenticationRequest);
    }

    @Test
    void testAuthenticateUser() {
        var authenticationRequest = new JwtAuthenticationRequestDto("john@example.com", "password");
        var responseDto = UserResponseDto.builder().id(1L).email("john@example.com").build();
        var user = User.builder()
                .id(1L)
                .username("john")
                .password("password")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .birthDate(LocalDate.now())
                .city("CityName")
                .createdAt(LocalDateTime.now())
                .modifiedAt(null)
                .build();

        when(mockUserRepository.findAll()).thenReturn(List.of(user));
        when(mockPasswordEncoder.matches(authenticationRequest.password(), user.getPassword())).thenReturn(Boolean.TRUE);
        when(mockJwtTokenProvider.generateToken(responseDto.getEmail())).thenReturn("test_access_token");

        authService.authenticateUser(authenticationRequest);
    }
}
