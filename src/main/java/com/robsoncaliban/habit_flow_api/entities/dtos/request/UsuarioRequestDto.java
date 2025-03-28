package com.robsoncaliban.habit_flow_api.entities.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Min(value = 5)
    String senha
) {}
