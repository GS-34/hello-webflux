package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Part13 {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1,3)
                .doOnNext(e->log.info("data : {} ",e))
                .materialize()
                .doOnNext(e -> log.info("signal :{}", e))
                .dematerialize()
                .collectList()
                .subscribe(r->log.info("result : {}",r));
    }

}
