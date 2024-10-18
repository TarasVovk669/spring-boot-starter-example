package com.sse.demo.repository;

import com.sse.demo.domain.CryptoTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoTransactionRepository extends ReactiveCrudRepository<CryptoTransaction, Integer> {
}
