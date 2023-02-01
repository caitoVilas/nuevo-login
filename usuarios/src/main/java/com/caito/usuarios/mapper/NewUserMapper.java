package com.caito.usuarios.mapper;

import com.caito.usuarios.dto.NewUserDTO;
import com.caito.usuarios.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewUserMapper {

    Usuario newUserDTOToUsuario(NewUserDTO request);
}
