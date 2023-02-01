package com.caito.usuarios.controller;

import com.caito.usuarios.dto.NewUserDTO;
import com.caito.usuarios.dto.UsuarioDTO;
import com.caito.usuarios.service.contract.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @Operation(description = "obtener usuario por id", summary = "obtener usuario por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "forbidden"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> getById(@PathVariable("id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.getById(id);
        if (usuarioDTO ==  null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    @Operation(description = "obtener listado de usuarios", summary = "obtener listado de  usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "forbidden"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Operation(description = "crear usuario", summary = "crear usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "401", description = "forbidden"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> createusuario(@RequestBody NewUserDTO newUserDTO){

        return new ResponseEntity<>(usuarioService.createUsuario(newUserDTO), HttpStatus.CREATED);
    }
}
