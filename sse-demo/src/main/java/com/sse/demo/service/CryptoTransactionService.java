package com.sse.demo.service;

import com.sse.demo.dto.CryptoTransactionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CryptoTransactionService {

    Mono<CryptoTransactionDTO> saveTransaction(Mono<CryptoTransactionDTO> mono);

    Flux<CryptoTransactionDTO> transactionStream();
}
