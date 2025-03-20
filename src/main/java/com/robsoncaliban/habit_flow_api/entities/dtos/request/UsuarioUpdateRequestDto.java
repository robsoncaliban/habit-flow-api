package com.robsoncaliban.habit_flow_api.entities.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateRequestDto(
    @NotBlank
    String nome,
    @NotBlank
    @Min(value = 5)
    String senha) {

}
