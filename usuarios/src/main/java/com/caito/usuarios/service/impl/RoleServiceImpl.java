package com.caito.usuarios.service.impl;

import com.caito.usuarios.entity.Role;
import com.caito.usuarios.enums.RoleEnum;
import com.caito.usuarios.repository.RoleRepository;
import com.caito.usuarios.service.contract.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role getByName(RoleEnum name) {
        return roleRepository.findByName(name);
    }
}
