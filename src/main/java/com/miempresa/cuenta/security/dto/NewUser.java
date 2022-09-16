package com.miempresa.cuenta.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class NewUser {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    // Por defecto crea un usuario normal
    // Si quiero un usuario Admin debo pasar este campo roles
    private Set<String> roles = new HashSet<>();

}
