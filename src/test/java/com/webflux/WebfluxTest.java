package com.webflux;


import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

public class WebfluxTest {

//    @Test
//    public void websocketClient() throws InterruptedException {
//
//        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
//        client.execute(
//                URI.create("http://localhost:8080/ws/echo"),
//                session -> {
//                    session.receive()
//                            .subscribe(webSocketMessage ->
//                                    System.out.println(webSocketMessage.getPayloadAsText()));
//
//                    return Flux
//                            .interval(Duration.ofMillis(1000))
//                            .map(String::valueOf)
//                            .map(session::textMessage)
//                            .as(session::send);
//                }
//        ).subscribe();
//
//        Thread.sleep(99999);
//    }
//
    @Test
  public void test(){


      String a = "";
      String b = "";

      if(a.equals(b)) {
        return;
      }
    }
}
