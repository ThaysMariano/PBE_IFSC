package ads.pbe.grpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.pbe.demo.GreeterGrpc;
import org.pbe.demo.HelloRequest;
import org.pbe.demo.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class GreeterClient {
    // aqui é o stub que possibilita o acesso ao serviço gRPC
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public GreeterClient() {
        // um channel define a localização do serviço gRPC
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        // cria o stub de forma a usar o channel
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();

        // faz a chamada a sayHello, e obtém a reposta
        HelloResponse response = blockingStub.sayHello(request);

        return response.getMessage();
    }
}