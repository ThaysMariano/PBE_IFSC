package com.example.mensagensTemporarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MensagensTemporariasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensagensTemporariasApplication.class, args);
	}


    HashMap<String, ArrayList<String>> msgs = new HashMap<>();
    String resposta;

    record Mensagem(String conteudo){}

    @GetMapping("/mensageiro/acessa/{chave}")
    public String obtem_mensagem(@PathVariable("chave") String chave) {

        if(msgs.get(chave)==null){
            resposta = "vazio, corrigir";
        }else{
            resposta = msgs.get(chave).removeFirst();
        }
        return resposta;
    }


    @PostMapping("/mensageiro/publica/{chave}")
    public String envia_mensagem(@PathVariable("chave") String chave, @RequestBody Mensagem mensagem) {

        ArrayList fila;

        //se nao possuir nada na chave cria uma nova lista, se ja existir so pega ela
        if(msgs.get(chave)==null){
            fila = new ArrayList<>();
        }else{
            fila = msgs.get(chave);
        }

        //add a msg na fila correspondente
        fila.add(mensagem.conteudo());

        //atualiza
        msgs.put(chave, fila);
        
        return chave + ", " + mensagem.conteudo();

    }
}
