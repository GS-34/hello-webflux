package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Part11 {

    public static void main(String[] args) throws InterruptedException {

        Flux.range(1,100)//1~100 생성
                .delayElements(Duration.ofMillis(1)) //1 밀리세컨드 마다 생성
                .sample(Duration.ofMillis(200))//200 밀리세컨드마다 최근에 본 값을 출력
                .subscribe(e -> log.info("onNext : {}", e));

        Thread.sleep(10000);
    }

}
