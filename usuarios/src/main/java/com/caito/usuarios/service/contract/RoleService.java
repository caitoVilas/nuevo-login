package com.caito.usuarios.service.contract;

import com.caito.usuarios.entity.Role;
import com.caito.usuarios.enums.RoleEnum;

public interface RoleService {

    Role getByName(RoleEnum name);
}
