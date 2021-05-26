package com.webflux.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SSE : Server Sent Event의 약어로 서버의 데이터를 실시간으로, 지속적으로 Streaming 하는 기술
 * - server -> client 단방향 Streaming
 * - HTTP 사용
 * - ContentType : "text/event-stream"
 *
 * https://boxfoxs.tistory.com/403
 */

@SpringBootApplication
public class ReactiveSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSseApplication.class, args);
    }
}
