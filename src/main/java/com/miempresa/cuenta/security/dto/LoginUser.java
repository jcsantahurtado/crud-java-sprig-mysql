package com.miempresa.cuenta.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginUser {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}