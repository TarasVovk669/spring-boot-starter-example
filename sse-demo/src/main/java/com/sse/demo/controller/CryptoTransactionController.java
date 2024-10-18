package com.sse.demo.controller;

import com.sse.demo.domain.Cryptocurrency;
import com.sse.demo.domain.TransactionType;
import com.sse.demo.dto.CryptoTransactionDTO;
import com.sse.demo.service.CryptoTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("transactions")
public class CryptoTransactionController {

    private final CryptoTransactionService service;

    @PostMapping
    public Mono<CryptoTransactionDTO> saveTransaction(@RequestBody Mono<CryptoTransactionDTO> mono) {
        return service.saveTransaction(mono);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CryptoTransactionDTO> transactionStream(
            @RequestParam("type") Optional<TransactionType> type,
            @RequestParam("from") Optional<Cryptocurrency> from,
            @RequestParam("to") Optional<Cryptocurrency> to,
            @RequestParam("minQuantity") Optional<BigDecimal> minQuantity) {
        return service.transactionStream()
                .filter(transaction -> type.map(t -> t.equals(transaction.getType())).orElse(true))
                .filter(transaction -> from.map(f -> f.equals(transaction.getCryptoFrom())).orElse(true))
                .filter(transaction -> to.map(t -> t.equals(transaction.getCryptoTo())).orElse(true))
                .filter(transaction -> minQuantity.map(q -> transaction.getQuantity().compareTo(q) >= 0).orElse(true));
    }
}
