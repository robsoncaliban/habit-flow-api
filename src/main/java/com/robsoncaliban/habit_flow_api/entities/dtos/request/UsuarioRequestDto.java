package com.robsoncaliban.habit_flow_api.entities.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UsuarioRequestDto(
    String nome,
    @Email
    String email,
    // TODO: Fazer validação personalizada
    @Min(value = 5)
    @Max(value = 15)
    String senha
) {}
