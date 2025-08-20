package com.example.SpringDataHorario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MeuController {

    record Data(String data){}
    record Horario(String hora){}

    @GetMapping("/data") //devolve json
    public Data obtem_data() {
        LocalDate data = LocalDate.now();
        return new Data(formatarData(data));
    }

    @GetMapping("/horario") //devolve json
    public Horario obtem_horario() {
        LocalTime hora = LocalTime.now();
        return new Horario(formatarHora(hora));
    }

    public String formatarData(LocalDate data){
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatData.format(data);
        return dataFormatada;
    }

    public String formatarHora(LocalTime hora){
        DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatHora.format(hora);
        return horaFormatada;

    }
}
