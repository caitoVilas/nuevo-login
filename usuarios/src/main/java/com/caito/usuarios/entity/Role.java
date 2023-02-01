package com.caito.usuarios.entity;

import com.caito.usuarios.enums.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
