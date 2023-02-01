package com.caito.usuarios.service.contract;

import com.caito.usuarios.dto.JwtDTO;
import com.caito.usuarios.dto.LoginDTO;
import com.caito.usuarios.dto.NewUserDTO;
import com.caito.usuarios.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO getById(Long id);
    List<UsuarioDTO> getAll();
    UsuarioDTO createUsuario(NewUserDTO usuario);
    JwtDTO login(LoginDTO loginDTO);
}
