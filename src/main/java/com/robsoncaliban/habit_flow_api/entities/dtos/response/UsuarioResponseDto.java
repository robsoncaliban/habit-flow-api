package com.robsoncaliban.habit_flow_api.entities.dtos.response;

import com.robsoncaliban.habit_flow_api.entities.Usuario;

public record UsuarioResponseDto(
    String nome,
    String email
) {
    public UsuarioResponseDto(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail());
    }
}
