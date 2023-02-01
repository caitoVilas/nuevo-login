package com.caito.usuarios.mapper;

import com.caito.usuarios.dto.RoleDTO;
import com.caito.usuarios.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleDTO roleToRoleDTO(Role request);
    List<RoleDTO> roleListToRoleDTOList(List<Role> request);
}
