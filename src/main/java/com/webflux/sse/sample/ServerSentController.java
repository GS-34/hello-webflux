package com.webflux.sse.sample;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ServerSentController {

    private Map<String, StocksService> stringStocksServiceMap;

    @GetMapping("/sse/stocks")
    public Flux<ServerSentEvent<?>> streamStocks() {
        return Flux
            .fromIterable(stringStocksServiceMap.values())//주식정보 flux 생성
            .flatMap(StocksService::stream)//Flux<StockItem> 변환
            .<ServerSentEvent<?>>map(item ->//StockItem->ServerSentEvent 변환
                ServerSentEvent
                    .builder(item)
                    .event("StockItem")
                    .id(item.getId())
                    .build()
            )
            .startWith(//구독 시작시 초기값 설정
                ServerSentEvent
                    .builder()
                    .event("Stocks")
                    .data(stringStocksServiceMap.keySet())
                    .build()
            );
    }
}
