import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

//Para criar um cliente HTTP, basta instanciar a classe HttpClient:
//Um objeto HttpClient é usado para enviar requsições e obter respostas.
        HttpClient cliente = HttpClient.newHttpClient();

//Para criar uma requisição, deve-se instanciar HttpRequest usando seu builder:
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://wiki.sj.ifsc.edu.br/"))
                .GET()
                .build();

//Para enviar uma requisição, deve-se usar o objeto HttpClient para enviar uma requisição representada por um objeto HttpRequest:
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

//O objeto HttpResponse representa uma resposta, e por ele se podem obter informações como código de status, cabeçalhos e corpo da resposta:
        System.out.printf("Status: %d\n", response.statusCode());
        System.out.printf("Corpo: %s\n", response.body());

    }
}