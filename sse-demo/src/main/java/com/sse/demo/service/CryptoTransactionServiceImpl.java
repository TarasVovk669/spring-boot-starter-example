package com.sse.demo.service;

import com.sse.demo.dto.CryptoTransactionDTO;
import com.sse.demo.repository.CryptoTransactionRepository;
import com.sse.demo.utils.EntityDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@AllArgsConstructor
public class CryptoTransactionServiceImpl implements CryptoTransactionService {

    private final CryptoTransactionRepository repository;
    private final Sinks.Many<CryptoTransactionDTO> cryptoSink;

    @Override
    public Mono<CryptoTransactionDTO> saveTransaction(Mono<CryptoTransactionDTO> mono) {
        return mono.map(EntityDtoMapper::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoMapper::toDto)
                .doOnNext(cryptoSink::tryEmitNext);
    }

    @Override
    public Flux<CryptoTransactionDTO> transactionStream() {
        return cryptoSink.asFlux();
    }
}
