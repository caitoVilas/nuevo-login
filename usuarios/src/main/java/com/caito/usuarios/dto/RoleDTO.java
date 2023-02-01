package com.caito.usuarios.dto;

import com.caito.usuarios.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private Long id;
    private RoleEnum name;
}
