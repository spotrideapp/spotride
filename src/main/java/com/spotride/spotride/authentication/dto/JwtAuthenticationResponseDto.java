package com.spotride.spotride.authentication.dto;

/**
 * DTO model for Authentication response.
 *
 * @param token generated token
 */
public record JwtAuthenticationResponseDto(String token) {}
