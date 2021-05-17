package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Part12 {

    public static void main(String[] args) throws InterruptedException {
        Flux.just(1,2,3)
                .concatWith(Flux.error(new RuntimeException("Conn Error")))
                .doOnEach(s ->
                        log.info("signal : {}", s))
                .subscribe();
    }

}
