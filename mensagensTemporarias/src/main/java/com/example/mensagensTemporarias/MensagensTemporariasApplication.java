package com.example.mensagensTemporarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MensagensTemporariasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensagensTemporariasApplication.class, args);
	}


    HashMap<String, String> hashmap = new HashMap<>();
    String resposta;




    @GetMapping("/mensageiro/acessa/{chave}")
    public String obtem_mensagem(@PathVariable("chave") String chave) {


        //teste
        hashmap.put("abc", "teste");

        //se existe a chave passada
        if(hashmap.containsKey(chave)){
            //pega a chave
            resposta = hashmap.get(chave);
        }

        return resposta;

    }



    @PostMapping("/mensageiro/pulica/{chave}")
    public String envia_mensagem(@PathVariable("chave") String chave) {


        return chave;

    }





}
