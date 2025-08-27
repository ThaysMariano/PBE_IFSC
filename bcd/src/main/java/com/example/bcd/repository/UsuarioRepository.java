package com.example.bcd.repository;

import com.example.bcd.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // busca todas entidades com este valor para coluna nome
    List<Usuario> findByFirstName(String nome);

    // busca todas entidades cuja coluna nome inicia com esta string
    List<Usuario> findByFirstNameStartingWith(String nome);

}