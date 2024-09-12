package com.acledabankplc.security.auth;

public interface AuthenticationService {
    AuthenticationResponse register(RegistrationRequest registrationRequest);
    AuthenticationResponse login(AuthenticateRequest authenticateRequest);
    boolean exist(String email);
}
