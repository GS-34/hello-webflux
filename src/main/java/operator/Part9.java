package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.LinkedList;

@Slf4j
public class Part9 {

    public static void main(String[] args) throws InterruptedException {

        log.info("=============buffer===============");
        //Buffer, 4개씩 끊어서 컬렉션을만듬
        Flux.range(1,13)
                .buffer(4)
                .subscribe(i -> log.info("onNext : {}",i));

        log.info("=============windowedFlux===============");

        //window 연산자
        //소수일때마다 스트림은 분할한다.
        Flux<Flux<Integer>> windowedFlux = Flux.range(101,20)
                .windowUntil(Part9::isPrime, true);

        windowedFlux.subscribe(
                window -> window
                        .collectList()
                        .subscribe(i ->
                                log.info("onNext : {}",i))
        );

        log.info("=============groupBy===============");
        Flux.range(1,7)
                .groupBy(e-> e % 2 == 0 ? "Even" : "Odd")
                .subscribe(groupFlux ->
                        groupFlux.scan(
                                new LinkedList<>(),
                                (list,elem)-> {
                                    list.add(elem);
                                    if(list.size() > 2) list.remove(0);
                                    return list;
                                }
                        ).filter(arr -> !arr.isEmpty())
                .subscribe(data ->
                        log.info("{} : {}", groupFlux.key(), data)));

    }

    static boolean isPrime(int n) {
        if( n<2 ) return false;
        for( int i=2; i<=(int)Math.sqrt(n); i++)
            if( n%i == 0) return false;
        return true;
    }

}
