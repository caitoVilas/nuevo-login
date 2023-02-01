package com.caito.usuarios.repository;

import com.caito.usuarios.entity.Role;
import com.caito.usuarios.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleEnum name);
}
