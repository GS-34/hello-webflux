package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Part7 {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(999,10)
                .parallel()
                .runOn(Schedulers.newParallel("PAR", 2))
                .map(a->a).subscribe(e -> log.info("onNext :{}", e));

    }

}
