package com.robsoncaliban.habit_flow_api.rest_controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoncaliban.habit_flow_api.entities.Usuario;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioRequestDto;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioUpdateRequestDto;
import com.robsoncaliban.habit_flow_api.entities.dtos.response.UsuarioResponseDto;
import com.robsoncaliban.habit_flow_api.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
    
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody @Valid UsuarioRequestDto usuario){
        var usuarioNovo = usuarioService.criarUsuario(usuario);
        var usuarioResponse = new UsuarioResponseDto(usuarioNovo);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @PatchMapping(value = "/{email}")
    public ResponseEntity<UsuarioResponseDto> updateUsuario(@PathVariable String email, @RequestBody @Valid UsuarioUpdateRequestDto usuarioDto){
        Usuario usuario = usuarioService.atualizarUsuario(email, usuarioDto);
        var usuarioResponse = new UsuarioResponseDto(usuario);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorEmail(@PathVariable String email){
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        var usuarioResponse = new UsuarioResponseDto(usuario);
        return ResponseEntity.ok().body(usuarioResponse);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletarContaPorEmail(email);
        return ResponseEntity.noContent().build();
    }



}
