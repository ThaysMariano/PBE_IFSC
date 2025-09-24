package ads.pbe.calculadora.service;


import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    static int acc;

    public CalculadoraService(){
         acc = 0;
    }

    public int getAcc(){
        return acc;
    }

    public static void soma(int num){
       acc +=num;
    }

    public static void reinicia(){
        acc = 0;
    }

    public static void subtrair(int num){
        acc -=num;
    }

    public static void multiplicar(int num){
        acc *=num;
    }



}
