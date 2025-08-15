import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

// Um tratador deve implementar a interface HttpHandler
class MeuHandler implements HttpHandler {

    // O método handle é chamado para processar uma requisição.
    // A requisição é representada por um objeto HttpExchange
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // obtém um OutputStream para gerar o corpo da mensagem de resposta
        OutputStream outputStream = httpExchange.getResponseBody();

        // Esta é a resposta a requisições tratadas por este HttpHandler !
        String htmlResponse = "<html><body><h1 style='color:red'>Hello World! em HTML \\o/</h1></body></html>";

        // Define o status da resposta, e o comprimento do corpo da mensagem  (em bytes)
        httpExchange.sendResponseHeaders(200, htmlResponse.length());

        //add o tipo da resṕosta
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", "text/html");

        // escreve o corpo da resposta
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    static class MeuHandlerPortugues implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();

            String htmlResponse = "Olá mundo!";

            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }


    static class MeuHandlerArquivo implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();

            //pegar uri - verificar tipo arquivo - listar arquivos de diretorio - iterar e criar o html



            String caminhoPadrao = "/home/aluno/pasta";
            File arvTxt = new File(caminhoPadrao+"/texto");

            String htmlResponse = "";

            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }



}


public class Main {

    public static void main(String[] args) throws Exception {
        // Cria um servidor HTTP, que recebe requisições em localhost no port 8080 (e com backlog 0)
        HttpServer srv = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        srv.createContext("/", new MeuHandler());
        srv.createContext("/portugues", new MeuHandler.MeuHandlerPortugues());
        srv.createContext("/a", new MeuHandler.MeuHandlerArquivo());

        // Executa o servidor HTTP
        srv.start();

    }



}