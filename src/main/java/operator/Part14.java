package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class Part14 {

    public static void main(String[] args) throws InterruptedException {
        Flux.push(emitter -> IntStream
        .range(1,1000)
        .forEach(e ->
                emitter.next(e)))
        .delayElements(Duration.ofMillis(1))
        .subscribe(e->log.info("onNext : {}", e));

        Thread.sleep(1000);
    }

}
