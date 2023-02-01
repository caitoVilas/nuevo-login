package com.caito.usuarios.security;

import com.caito.usuarios.entity.Usuario;
import com.caito.usuarios.exceptions.BadRequestException;
import com.caito.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (!usuario.isPresent())
            throw new BadRequestException("el usuario no existe");
        return UsuarioPrincipal.build(usuario.get());
    }
}
