package com.acledabankplc.security.auth;

import lombok.Data;

@Data
public class AuthenticateRequest {
     String email;
     String password;

}
