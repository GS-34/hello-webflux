package operator;


import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.stream.Stream;

@Slf4j
public class Part2 {

    public static void main(String[] args) throws InterruptedException {

        //collectList, collectSortedList
        //Mono<List<T>> 리스트를 반환한다.
        Flux.just(1,6,2,8,3,1,5,1)
                .collectSortedList(Comparator.reverseOrder())
                .subscribe(System.out::println);

        //Mono<Map<K, T>> map 반환한다.
        Flux.just(1,6,2,8,3,1,5,1)
                .collectMap(e -> e + "key")//키값을 생성
                .subscribe(System.out::println);


        //Mono<Map<K, Collection<T>>> map 반환한다.
        Flux.just(1,6,2,8,3,1,5,1)
                .collectMultimap(e -> e + "key")//키값을 생성
                .subscribe(System.out::println);


        Flux.just(1,6,2,8,3,1,5,1)
                .distinct()//중복을제거
                .subscribe(System.out::print);

        System.out.println();

        Flux.just(1,6,2,8,3,1,5,1,1,1,1,2)
                .distinctUntilChanged()//직전 값과 비교해서 중복일경우 제거
                .subscribe(System.out::print);


    }

}
