package com.miempresa.cuenta.security.repository;

import com.miempresa.cuenta.security.entity.Role;
import com.miempresa.cuenta.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Notación que indica que es un repositorio
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(RoleName roleName);

}
