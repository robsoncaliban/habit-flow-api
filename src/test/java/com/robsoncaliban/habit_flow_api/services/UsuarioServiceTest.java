package com.robsoncaliban.habit_flow_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.robsoncaliban.habit_flow_api.entities.Usuario;
import com.robsoncaliban.habit_flow_api.entities.dtos.request.UsuarioRequestDto;
import com.robsoncaliban.habit_flow_api.entities.dtos.response.UsuarioResponseDto;
import com.robsoncaliban.habit_flow_api.repositories.UsuarioRepository;
import com.robsoncaliban.habit_flow_api.services.exceptions.UsuarioNaoEncontradoException;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioRequestDto usuarioRequest;

    @BeforeEach
    void initAll(){
        var usuario01 = new Usuario("Julio", "123456", "julio@test");
        var usuario02 = new Usuario("Marcos", "123456", "marcos@test");
        var usuario03 = new Usuario("Edna", "123456", "edna@test");
        usuarioRepository.saveAll(Arrays.asList(usuario01,usuario02,usuario03));
    }
    @AfterEach
    void finalAll(){
        usuarioRepository.deleteAll();
    }

    @Test
    void testCriarUsuario() {
        usuarioRequest = new UsuarioRequestDto("Ana", "ana@gmail.com", "12345");
        UsuarioResponseDto usuarioResponse = usuarioService.criarUsuario(usuarioRequest);
        assertNotNull(usuarioResponse);
    }

    @Test
    void testBuscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        assertEquals(3, usuarios.size());
    }

    @Test
    void testBuscarUsuarioPorEmail() {
        Usuario uResponseDto = usuarioService.buscarUsuarioPorEmail("julio@test");
        assertNotNull(uResponseDto);
    }

    @Test
    void testDeletarContaPorEmail() {
        usuarioService.deletarContaPorEmail("edna@test");
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarUsuarioPorEmail("edna@test");
        });
    }

}
