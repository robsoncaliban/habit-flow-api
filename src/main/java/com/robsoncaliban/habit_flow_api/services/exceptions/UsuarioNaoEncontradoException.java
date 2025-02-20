package com.robsoncaliban.habit_flow_api.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String email) {
        super("Usuario com o email: " + email + " não encontrado");
    }
}
