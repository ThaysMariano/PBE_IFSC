package ads.pbe.ecoMensagem.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WsHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new ArrayList<>();

    // este record representa a mensagem a ser enviada para o cliene
    record Dado(int id, String conteudo) {}

    // um ObjectMapper é disponibilizado pelo Spring para codificação JSON
    // Ele é implementado pela API Jackson
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            for (int j = 0; j < sessions.size(); j++) {
                if (!session.getId().equals(sessions.get(j).getId())) {
                    // cria um objeto Dado
                    Dado dado = new Dado(j, message.getPayload());

                    // converte o objeto Dado para JSON
                    String conteudo = mapper.writeValueAsString(dado);

                    // encapsula a string JSON em uma mensagem a ser enviada para o cliente
                    TextMessage msg = new TextMessage(conteudo);
                    sessions.get(j).sendMessage(msg);
                }
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // chama o método herdado ... ele deve fazer algo importante
        super.afterConnectionEstablished(session);
        // faz algo de interessante quando o websocket estiver conectado
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        // chama o método herdado ... também deve fazer algo importante
        super.afterConnectionClosed(session, status);
        // e faz algo necessário (?!) quando esse websocket for fechado
        sessions.remove(session);
    }
}
