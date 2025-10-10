package ads.pbe.soap;
import org.springframework.stereotype.Service;

@Service
public class MySoapServiceImpl implements MySoapService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " from CXF SOAP!";
    }
}