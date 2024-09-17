package com.acledabankplc.config.auth;


import java.security.Principal;

public interface AuthenticationService {
    AuthenticationResponse register(RegistrationRequest registrationRequest);
    AuthenticationResponse login(AuthenticateRequest authenticateRequest);
    boolean exist(String email);

}
