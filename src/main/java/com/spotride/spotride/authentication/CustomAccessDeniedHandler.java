package com.spotride.spotride.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Authentication denied handler.
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        log.debug("Access Denied: {}", accessDeniedException.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        var errorMessage = "Access Denied: You do not have permission to access this resource.";
        var jsonResponse = String.format("{\"status\": \"error\", \"message\": \"%s\", \"code\": 403}", errorMessage);

        response.getWriter().write(jsonResponse);
    }
}
