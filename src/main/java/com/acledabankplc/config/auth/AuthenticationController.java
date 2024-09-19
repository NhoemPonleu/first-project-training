package com.acledabankplc.config.auth;

import com.acledabankplc.exception.AlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller"
        , description = "This Controller Allow Internal User To Register And Manage Role" +
        "In System For Do Something In System")

public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);
    private final AuthenticationService authenticationService;

    @PostMapping("/authentication")
    @Operation(summary = "User Authentication"
            , description = "This endpoint Allow User to Authentication for Use this System ")
    public ResponseEntity<?> registerNewUser(@RequestBody RegistrationRequest authenticationRequest) {
        if (authenticationService.exist(authenticationRequest.email)) {
            throw new AlreadyExistsException("Email is already in use: " + authenticationRequest.getEmail());
        }
        AuthenticationResponse authenticationResponse = authenticationService.register(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);

    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate"
            , description = "This endpoint After Authentication User Can Login Follow On Role")
    public ResponseEntity<?> login(@RequestBody AuthenticateRequest authenticateRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.login(authenticateRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
