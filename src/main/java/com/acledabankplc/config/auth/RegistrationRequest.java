package com.acledabankplc.config.auth;

import com.acledabankplc.config.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    Role role;

}

