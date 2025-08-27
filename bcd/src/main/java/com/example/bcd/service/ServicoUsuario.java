package com.example.bcd.service;

import com.example.bcd.entidade.Usuario;
import com.example.bcd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;

    public long cria(String nome, String sobrenome) {
        Usuario usuario = new Usuario();
        usuario.setFirstName(nome);
        usuario.setSobrenome(sobrenome);

        usuario = usuarioRepository.save(usuario);
        return usuario.getId();
    }

    public Usuario buscaPorNome(String nome) {

        return usuarioRepository.findFirstByFirstName(nome);
    }

    public List<Usuario> buscaTodosComNome(String nome) {
        Usuario usuario = new Usuario();
        usuario.setFirstName(nome);
        Example<Usuario> exemplo = Example.of(usuario);

        return usuarioRepository.findAll(exemplo);
    }
}