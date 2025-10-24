package pbe;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.cef.callback.CefMenuModel;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

@RestController
public class SSEController {

    record Mensagem(String msg){}


    /*
    Handles connection to the event service
    Starts a thread for each client.
     */
    @GetMapping("/eventos")
    public SseEmitter streamSseEvents() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        // uses IP:port as an ID of this connection

        // creates the thread
        Thread.ofVirtual().start(() -> {
            try {
                // forever waits for events to send to client ...
                for (int n=0; n < 10; n++) {
                    LocalDateTime agora = LocalDateTime.now();
                    String msg = String.format("mensagem %d: %s", n, agora);
                    // envia o evento
                    emitter.send(msg);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @PostMapping("/envia")
    public SseEmitter envia(@RequestBody Mensagem msg) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        Thread.ofVirtual().start(() -> {
            try {
                emitter.send(msg);
                emitter.complete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return emitter;
    }

}