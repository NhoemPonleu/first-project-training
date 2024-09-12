package com.acledabankplc.config.auth;

import com.acledabankplc.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);
    private final AuthenticationService authenticationService;
    @PostMapping("/authentication")
    public ResponseEntity<?>registerNewUser(@RequestBody RegistrationRequest authenticationRequest){
        if (authenticationService.exist(authenticationRequest.email)){
            throw new AlreadyExistsException("Email is already in use: " + authenticationRequest.getEmail());
        }
       AuthenticationResponse authenticationResponse= authenticationService.register(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);

    }
    @PostMapping("/authenticate")
    public ResponseEntity<?>login(@RequestBody AuthenticateRequest authenticateRequest){
        AuthenticationResponse authenticationResponse= authenticationService.login(authenticateRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
