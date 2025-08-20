package org.pbe.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//gradle bootrun

@SpringBootApplication
@RestController
public class DemoApplication {
    private int contador = 0;
    record Mensagem(String mensagem, int contador) {} //obj simplificado - imutavel

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world !";
    }

    @RequestMapping("/ola")
    public String ola() {
        return "OLA MUNDOOO!";
    }


    @RequestMapping("/msg") //devolve json
    public Mensagem msg() {
        contador++;
        return new Mensagem("Ola mundo ", contador);
    }
}
