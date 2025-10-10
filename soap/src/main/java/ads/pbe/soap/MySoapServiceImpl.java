package ads.pbe.soap;
import org.springframework.stereotype.Service;

@Service
public class MySoapServiceImpl implements MySoapService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " from CXF SOAP!";
    }

    @Override
    public int soma(int num1, int num2) {
        return num1+num2;
    }

    @Override
    public int subtracao(int num1, int num2) {
        return num1-num2;
    }

    @Override
    public int multiplicacao(int num1, int num2) {
        return num1*num2;
    }

    @Override
    public String divisao(int num1, int num2) {
        if (num2==0){
            return "Divisão não permitida";
        }else{
            return String.valueOf(num1/num2);
        }
    }


}