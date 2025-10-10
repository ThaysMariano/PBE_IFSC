package ads.pbe.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC) // Or SOAPBinding.Style.DOCUMENT
public interface MySoapService {

    @WebMethod
    String sayHello(String name);
}