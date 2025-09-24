package ads.pbe.calculadora.controller;

import ads.pbe.calculadora.service.CalculadoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @PutMapping("soma")
    public ResponseEntity<Calc> soma(@RequestBody Valor valor)  {
       CalculadoraService.soma(valor.num);
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }

    //reiniciar
    @PutMapping("reinicio")
    public ResponseEntity<Calc> reinicio()  {
        calculadoraService.reinicia();
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }

    //subtracao
    @PutMapping("subtracao")
    public ResponseEntity<Calc> subtracao(@RequestBody Valor valor)  {
        CalculadoraService.subtrair(valor.num);
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }

    //multiplicacao
    @PutMapping("multiplicacao")
    public ResponseEntity<Calc> multiplicacao(@RequestBody Valor valor)  {
        CalculadoraService.multiplicar(valor.num);
        return  ResponseEntity.ok(new Calc(true, calculadoraService.getAcc()));
    }







}
