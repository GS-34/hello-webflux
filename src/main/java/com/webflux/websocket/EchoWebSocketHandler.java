package com.webflux.websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * WebSocketHandler 웹소켓 연결을 처리하기 위한 인터페이스
 */
public class EchoWebSocketHandler implements WebSocketHandler {

    /**
     * handle()
     * session 을 허용하는 handle 메소드
     *
     * WebSocketSession
     * - 핸드셰이크
     * - 세션 속성 및 수신
     * - 데이터 스트림등의 정보 액세스
     */
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session
            .receive()//인바운드 스트림에 접근, 스트림을 통해 들어온 데이터는 WebSocketMessage
            .map(WebSocketMessage::getPayloadAsText)
            .map(tm -> "Echo: " + tm)
            .map(session::textMessage)
            .as(session::send);
    }
}
