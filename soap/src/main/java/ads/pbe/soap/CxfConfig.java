package ads.pbe.soap;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    private final Bus bus;
    private final MySoapService mySoapService;

    public CxfConfig(Bus bus, MySoapService mySoapService) {
        this.bus = bus;
        this.mySoapService = mySoapService;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, mySoapService);
        endpoint.publish("/hello"); // A URI onde seu webservice é acessível
        return endpoint;
    }
}