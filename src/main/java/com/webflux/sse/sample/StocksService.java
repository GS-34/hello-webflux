package com.webflux.sse.sample;

import reactor.core.publisher.Flux;

public interface StocksService {

    Flux<StockItem> stream();
}