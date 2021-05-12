package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Part4 {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1,10);

        flux.reduce(0, (acc, elem) -> acc+elem)//최종결과만 onNext
                .subscribe(e -> log.info("reduce result : {}", e));

        flux
                .scan(0, (acc, elem) -> acc+elem)//고ㅏ정도 함께 onNext
                .subscribe(e -> log.info("scan result : {}", e));

    }

}
