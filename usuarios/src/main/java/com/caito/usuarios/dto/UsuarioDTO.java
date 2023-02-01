package com.caito.usuarios.dto;

import com.caito.usuarios.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {

    private Long id;
    private String username;
    private String email;
    private List<RoleDTO> roles;
}
