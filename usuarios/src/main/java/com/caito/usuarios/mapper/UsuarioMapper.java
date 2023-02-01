package com.caito.usuarios.mapper;

import com.caito.usuarios.dto.UsuarioDTO;
import com.caito.usuarios.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO usuarioTousarioDTO(Usuario request);
    List<UsuarioDTO> usuarioListToUsuarioDTOList(List<Usuario> request);
}
