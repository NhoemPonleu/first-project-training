package com.acledabankplc.security.auth;

import com.acledabankplc.security.Role;
import lombok.Data;

@Data
public class RegistrationRequest{
    String firstName;
    String lastName;
    String email;
    String password;
    Role role;

}

