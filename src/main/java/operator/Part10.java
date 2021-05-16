package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.LinkedList;

@Slf4j
public class Part10 {

    public static void main(String[] args) throws InterruptedException {

        Flux.just("user-1","user-2","user-3")
                .flatMap(u ->
                        requestBook()
                        .map(b-> u + " / "+b))
                .subscribe(r ->
                        log.info("onNext : {}", r));

        Thread.sleep(1000);

    }

    static Flux requestBook(){
        return Flux.range(1, 10)
                .map(i->"book-" + i)
                .delayElements(Duration.ofMillis(3));
    }

}
