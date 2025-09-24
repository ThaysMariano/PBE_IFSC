package ads.pbe.calculadora.controller;

import ads.pbe.calculadora.service.CalculadoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CalculadoraController {

    record Calc(boolean sucesso, int result){}
    record Valor(int num){}

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }


    //mostrar o valor atual do acc
    @GetMapping("calculadora")
    public ResponseEntity<Calc> cria()  {
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }

    //soma
    @PostMapping("soma")
    public ResponseEntity<Calc> soma(@RequestBody Valor valor)  {
       CalculadoraService.soma(valor.num);
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }






}
