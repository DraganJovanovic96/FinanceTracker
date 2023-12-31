package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.dto.AuthenticationRequestDto;
import com.talentwunder.financetracker.dto.AuthenticationResponseDto;
import com.talentwunder.financetracker.dto.RegisterRequestDto;
import com.talentwunder.financetracker.service.impl.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Controller class for handling authentication-related API endpoints.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    /**
     * The service used to for authentication.
     */
    private final AuthenticationService service;

    /**
     * Registers a new user.
     *
     * @param request the registration request containing user details
     * @return the ResponseEntity containing the authentication response
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Authenticates a user.
     *
     * @param request the authentication request containing user credentials
     * @return the ResponseEntity containing the authentication response
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     * Refreshes the authentication token.
     *
     * @param request  the HttpServletRequest containing the refresh token
     * @param response the HttpServletResponse for setting the new token in the response header
     * @throws IOException if an I/O error occurs while refreshing the token
     */
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
