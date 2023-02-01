package com.caito.usuarios.controller;

import com.caito.usuarios.dto.JwtDTO;
import com.caito.usuarios.dto.LoginDTO;
import com.caito.usuarios.service.contract.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autorizacion")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<JwtDTO> login(@RequestBody LoginDTO dto){

        return new ResponseEntity<>(usuarioService.login(dto), HttpStatus.OK);
    }
}
