package com.caito.usuarios.service.impl;

import com.caito.usuarios.dto.JwtDTO;
import com.caito.usuarios.dto.LoginDTO;
import com.caito.usuarios.dto.NewUserDTO;
import com.caito.usuarios.dto.UsuarioDTO;
import com.caito.usuarios.entity.Role;
import com.caito.usuarios.entity.Usuario;
import com.caito.usuarios.enums.RoleEnum;
import com.caito.usuarios.exceptions.BadRequestException;
import com.caito.usuarios.mapper.NewUserMapper;
import com.caito.usuarios.mapper.UsuarioMapper;
import com.caito.usuarios.repository.UsuarioRepository;
import com.caito.usuarios.security.jwt.JwtProvider;
import com.caito.usuarios.service.contract.RoleService;
import com.caito.usuarios.service.contract.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private NewUserMapper newUserMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);


    @Override
    public UsuarioDTO getById(Long id) {
        logger.info("inicio servio buscar usuario por id");
        return usuarioMapper.usuarioTousarioDTO(usuarioRepository.findById(id).orElse(null));
    }

    @Override
    public List<UsuarioDTO> getAll() {
        logger.info("inicio servicio mostrar usuarios");
        return usuarioMapper.usuarioListToUsuarioDTOList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioDTO createUsuario(NewUserDTO usuario) {
        logger.info("inicio servicio guardar usuario");
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            logger.error("el email ya esta registrado");
            throw new BadRequestException("el email ya esta registrado");
        }

        if (usuarioRepository.existsByUsername(usuario.getUsername())){
            logger.error(" el nombre de usuario ya esta registrado");
            throw new BadRequestException("el nombre de usuario ya esta registrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (RoleEnum re: usuario.getRoles()){
            Role role = roleService.getByName(re);
            roles.add(role);
        }

        Usuario us = new Usuario();
        us.setUsername(usuario.getUsername());
        us.setEmail(usuario.getEmail());
        us.setPassword(usuario.getPassword());
        us.setRoles(roles);
        return usuarioMapper.usuarioTousarioDTO(usuarioRepository
                .save(us));
    }

    @Override
    public JwtDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtDTO(token);
    }
}
