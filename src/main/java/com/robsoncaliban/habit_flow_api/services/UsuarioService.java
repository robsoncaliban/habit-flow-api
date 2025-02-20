package com.robsoncaliban.habit_flow_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robsoncaliban.habit_flow_api.entities.Usuario;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioRequestDto;
import com.robsoncaliban.habit_flow_api.entities.dtos.response.UsuarioResponseDto;
import com.robsoncaliban.habit_flow_api.repositories.UsuarioRepository;
import com.robsoncaliban.habit_flow_api.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto usuarioDto){
        // TODO: fazer regra de negocio 
        // 01 - verificar se o email é valido atraves do envio de email
        // 02 - não deixar cadastrar email igual
        var usuarioNovo = new Usuario(usuarioDto.nome(), usuarioDto.senha(), usuarioDto.email());
        return new UsuarioResponseDto(usuarioRepository.save(usuarioNovo));
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> 
            new UsuarioNaoEncontradoException(email));
    }

    public void deletarContaPorEmail(String email){
        var usuarioExcluir = buscarUsuarioPorEmail(email);
        usuarioRepository.delete(usuarioExcluir);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
}
