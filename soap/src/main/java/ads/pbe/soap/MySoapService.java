package ads.pbe.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC) // Or SOAPBinding.Style.DOCUMENT
public interface MySoapService {

    @WebMethod
    String sayHello(String name);

    @WebMethod
    int soma(int num1, int num2);

    @WebMethod
    int subtracao(int num1, int num2);

    @WebMethod
    int multiplicacao(int num1, int num2);

    @WebMethod
    String divisao(int num1, int num2);


}