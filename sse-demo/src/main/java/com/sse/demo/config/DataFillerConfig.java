package com.sse.demo.config;

import com.sse.demo.domain.CryptoTransaction;
import com.sse.demo.domain.Cryptocurrency;
import com.sse.demo.domain.TransactionType;
import com.sse.demo.dto.CryptoTransactionDTO;
import com.sse.demo.service.CryptoTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Component
@AllArgsConstructor
public class DataFillerConfig implements CommandLineRunner {

    private final CryptoTransactionService service;

    @Override
    public void run(String... args) throws Exception {
        Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(1))
                .map(element -> getRandomCryptoTransaction())
                .flatMap(dto -> this.service.saveTransaction(Mono.just(dto)))
                .subscribe();
    }

    private CryptoTransactionDTO getRandomCryptoTransaction() {
        var cryptoTypes = Cryptocurrency.values();
        var types = TransactionType.values();

        return CryptoTransactionDTO.builder()
                .date(LocalDateTime.now())
                .type(types[ThreadLocalRandom.current().nextInt(types.length)])
                .cryptoFrom(cryptoTypes[ThreadLocalRandom.current().nextInt(cryptoTypes.length)])
                .cryptoTo(cryptoTypes[ThreadLocalRandom.current().nextInt(cryptoTypes.length)])
                .quantity(new BigDecimal(ThreadLocalRandom.current().nextInt(1, 100_000)))
                .build();
    }
}
