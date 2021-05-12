package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Slf4j
public class Part3 {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(3,5,7,9,11,15,16,17);

        flux.any(e-> e % 2 == 0)//해당 조건 부합하는 원소가 하나라도 있는가?
                .subscribe(has -> log.info("has : {}",has));

        flux.all(e-> e % 2 == 0)//모든 원소가 해당 조건에 부합하는가?
                .subscribe(all -> log.info("all : {}", all));

        flux.count()//스트림 카운트 반환
                .subscribe(c -> log.info("count : {}", c));

        flux.hasElement(18)//해당원소가 있는가?
                .subscribe(has -> log.info("has : {}", has));

        flux.hasElements()//원소가 존재하는가?
                .subscribe(has -> log.info("has : {}", has));
    }

}
