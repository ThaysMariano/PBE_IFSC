package ads.pbe.grpc;

import org.pbe.demo.GreeterGrpc;
import io.grpc.stub.StreamObserver;
import org.pbe.demo.HelloRequest;
import org.pbe.demo.HelloResponse;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        String message = "Hello, " + name + "!";
        HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();
        // envia a resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
