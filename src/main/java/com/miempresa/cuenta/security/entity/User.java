package com.miempresa.cuenta.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase para la base de datos
 */
@Entity
@Getter @Setter
public class User {
    //Id de la tabla
    @Id
    //Id Auto Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    //Decorador para indicar que no puede ser null el campo
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    @NotNull
    // Relación many to many
    // Un usuario puede tener MUCHOS roles y un rol puede PERTENECER a varios usuarios
    // Tabla intermedia que tiene dos campos que va a tener userId y roleId
    @ManyToMany
    // join columns hace referencia a la columna que hace referencia hacia esta
    // Es decir la tabla usuario_rol/user_role va a tener un campo que se llama id_usuario/user_id
    // inverseJoinColumns = el inverso, hace referencia a rol
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    // Constuctor sin Id ni Roles
    public User(@NotNull String name,
                @NotNull String username,
                @NotNull String email,
                @NotNull String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
