package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class Part1 {

    public static void main(String[] args) throws InterruptedException {
        //Long.MAX_VALUE
        Flux.just("A","B","C","D")
                .subscribe(
                        data -> log.info("onNext : {}", data),
                        err -> log.error("onError : {}", err.getMessage()),
                        () -> log.info("onComplete"));

        System.out.println("========================================================");

        //request(4)
        Flux.range(1,100)
                .subscribe(
                        data -> log.info("onNext : {}", data),
                        err -> log.error("onError : {}", err.getMessage()),
                        () -> log.info("onComplete"),
                        subscription -> {
                            subscription.request(4);
                            subscription.cancel();
                        });

        System.out.println("========================================================");
        //Disposable 을 이용해서 스트림을 취소
        Disposable disposable = Flux.interval(Duration.ofMillis(50))
                .subscribe(
                        data -> log.info("onNext : {}", data));
        log.info("isDisposed : {}", disposable.isDisposed());
        Thread.sleep(1000);
        disposable.dispose();
        log.info("isDisposed : {}", disposable.isDisposed());

        System.out.println("========================================================");

        Flux.range(2018,5)//2018~2022까지 생성 Flux<Integer>
                .timestamp()//타임스템프 추가 Flux<Tuple2<Long, Integer>>
                .index()//index 추가 Flux<Tuple2 <Long, Tuple2<Long, Integer>>
                .subscribe(
                        e-> log.info("index : {}, ts : {}, value : {}",
                                e.getT1(),
                                Instant.ofEpochMilli(e.getT2().getT1()),
                                e.getT2().getT2())
                );

        System.out.println("========================================================");

    }

}
