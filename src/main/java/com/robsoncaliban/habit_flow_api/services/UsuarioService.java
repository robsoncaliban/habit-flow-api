package com.robsoncaliban.habit_flow_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robsoncaliban.habit_flow_api.entities.Usuario;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioRequestDto;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioUpdateRequestDto;
import com.robsoncaliban.habit_flow_api.services.exceptions.DuplicateDataException;
import com.robsoncaliban.habit_flow_api.services.exceptions.UsuarioNaoEncontradoException;
import com.robsoncaliban.habit_flow_api.utils.BeanUtilsIgnoreNull;

@Service
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(UsuarioRequestDto usuarioDto){
        if (usuarioRepository.findByEmail(usuarioDto.email()).isPresent()) {
            throw new DuplicateDataException(usuarioDto.email());
        }
        var usuarioNovo = new Usuario(usuarioDto.nome(), usuarioDto.senha(), usuarioDto.email());
        return usuarioRepository.save(usuarioNovo);
    }

    public Usuario atualizarUsuario(String email, UsuarioUpdateRequestDto usuarioDto){
        Usuario usuario = buscarUsuarioPorEmail(email);
        BeanUtilsIgnoreNull.copyProperties(usuarioDto, usuario);
        return usuarioRepository.save(usuario);
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
