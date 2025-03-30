package com.spotride.spotride.authentication;

import com.spotride.spotride.authentication.controller.AuthController;
import com.spotride.spotride.authentication.dto.JwtAuthenticationRequestDto;
import com.spotride.spotride.authentication.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AuthController.class)
@ExtendWith(SpringExtension.class)
public class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private AuthController authController;

    @Test
    void testRegisterUser() {
        var authenticationRequest = new JwtAuthenticationRequestDto("john@example.com", "password");
        when(authService.registerUser(authenticationRequest)).thenReturn("test_access_token");
        authController.registerUser(authenticationRequest);
    }

    @Test
    void testAuthenticateUser() {
        var authenticationRequest = new JwtAuthenticationRequestDto("john@example.com", "password");
        when(authService.registerUser(authenticationRequest)).thenReturn("test_access_token");
        authController.authenticateUser(authenticationRequest);
    }
}
