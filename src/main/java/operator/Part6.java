package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class Part6 {

    public static void main(String[] args) throws InterruptedException {

        /**
         * concat(flux 를 연결한다.)
         * Flux1 ,2,3 순차 subscribing
        * */
        log.info("=============concat===============");
        Flux.concat(
                Flux.range(1,3),
                Flux.range(4,2),
                Flux.range(6,5)
        ).subscribe(e -> log.info("onNext :{}", e));


        /**
         * merge
         * 1,2,3 Flux 동시 subscribing
         * */
        log.info("=============merge===============");
        Flux<String> flux1 = Flux.just("Hello", "Vikram");

        Flux.merge(
                Flux.interval(Duration.ofMillis(3000))
                        .zipWith(flux1, (i, msg) -> msg),
                Flux.range(999,10)
                        .parallel()
                        .runOn(Schedulers.parallel())
                        .doOnNext(
                        e-> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                ),
                Flux.range(20,10).doOnNext(
                        e-> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                )
        ).subscribe(e -> log.info("onNext :{}", e));

        log.info("=============zip===============");

        /**
         * zip
         * 두개의 flux 의 원소를 조합
         * */
        Flux<String> sFlux = Flux.just("S","T","R","I","N","G");
        Flux.range(20,10)
                .zipWith(sFlux,(a,b)->a+b)
                .subscribe(e -> log.info("onNext :{}", e));


        log.info("=============zip===============");

    }

}
