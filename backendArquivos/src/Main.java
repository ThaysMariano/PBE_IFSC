import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

// Um tratador deve implementar a interface HttpHandler
class MeuHandler implements HttpHandler {

    // O método handle é chamado para processar uma requisição.
    // A requisição é representada por um objeto HttpExchange
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        //Atividade: um backend capaz de fornecer arquivos de acordo com as URI das requisições recebidas.

        //pega a URI
        URI uri = httpExchange.getRequestURI();

        //transforma a URI em um caminho completo do arquivo CAMINHO WINDOWS!!
        Path caminhoReal = Paths.get("C:/Users/thays/Desktop/pastaAtv/" + uri.getPath());

        // obtém um OutputStream para gerar o corpo da mensagem de resposta
        OutputStream outputStream = httpExchange.getResponseBody();

        StringBuilder htmlResponse = new StringBuilder();

        //erro 404 caos nao ache
        if (!Files.exists(caminhoReal)) {
            htmlResponse = new StringBuilder("<html><body><h1>404! Path not found!</h1></body></html>");
            httpExchange.sendResponseHeaders(404, htmlResponse.length());
            outputStream.write(htmlResponse.toString().getBytes());
            outputStream.close();
            return;
        }

        //se o resultado do caminho é um diretorio:
        if(Files.isDirectory(caminhoReal)){

            String caminhoUri = uri.getPath();

            //cria html basico com titulo do arq atual
            htmlResponse.append("<html><body><h1>Listagem de /").append(caminhoReal.getFileName()).append("</h1>");

            //comeca a lista
            htmlResponse.append("<ul>");

            // --
            File arquivos = caminhoReal.toFile();

            File[] listagem = arquivos.listFiles();

            if(listagem == null){
                htmlResponse.append("<h2>Diretório Vazio!</h2>");
            }else{
                //percorre os aqruivos dentro desse diretorio
                for(File f : Objects.requireNonNull(arquivos.listFiles())){
                    htmlResponse.append("<li><a href='").append(caminhoUri.endsWith("/")? caminhoUri : STR."\{caminhoUri}/").append(f.getName()).append("'>").append(f.getName()).append("</a></li>");
                }
                //fecha a lista
                htmlResponse.append("</ul>");
            }

            htmlResponse.append("</body></html>");




            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", "text/html");

            httpExchange.sendResponseHeaders(200, htmlResponse.length());

            String resposta = htmlResponse.toString();

            outputStream.write(resposta.getBytes());
            outputStream.flush();
            outputStream.close();


        }
        //se o caminho da em um arquivo
        if(Files.isRegularFile(caminhoReal)){

            //descobre o tipo (extensao) do arquivo
            String tipo = Files.probeContentType(caminhoReal);

            //se for vazio (ver o que colocar)
            if(tipo==null){
                return;
            }

            //pega os bytes
            byte[] bytes = Files.readAllBytes(caminhoReal);

            //cria o header com o tipo e tamanho definido
            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", tipo);

            httpExchange.sendResponseHeaders(200, bytes.length);

            OutputStream output =httpExchange.getResponseBody();

            output.write(bytes);
            output.flush();
            output.close();



        }


    }

}


public class Main {

    public static void main(String[] args) throws Exception {
        // Cria um servidor HTTP, que recebe requisições em localhost no port 8080 (e com backlog 0)
        HttpServer srv = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        srv.createContext("/", new MeuHandler());

        // Executa o servidor HTTP
        srv.start();

    }



}