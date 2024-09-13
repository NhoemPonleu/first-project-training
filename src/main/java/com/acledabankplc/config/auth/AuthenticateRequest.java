package com.acledabankplc.config.auth;

import lombok.Data;

@Data
public class AuthenticateRequest {
    String email;
    String password;

}
