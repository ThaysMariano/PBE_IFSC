package com.example.bcd;

import com.example.bcd.entidade.Usuario;
import com.example.bcd.service.ServicoUsuario;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    //serviceuser <-usa nos get e post
    //get e post

    @Autowired
    ServicoUsuario userServ;

    @GetMapping("/acessa/{nome}")
    public Usuario obtem_usuario(@PathVariable("nome") String nome){
        return userServ.buscaPorNome(nome);
    }

    @GetMapping("/acessaTodos/{nome}")
    public List<Usuario> obtem_usuarios(@PathVariable("nome") String nome){
        return userServ.buscaTodosComNome(nome);
    }

    @PostMapping("/adiciona")
    public Long adiciona_usuario(@Param("nome") String nome, @Param("sobrenome") String sobrenome){
        return userServ.cria(nome, sobrenome);
    }








}
