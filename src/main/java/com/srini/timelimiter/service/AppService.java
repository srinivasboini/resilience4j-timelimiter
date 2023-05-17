package com.srini.timelimiter.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * The type App service.
 */
@Service
@Slf4j
public class AppService {


    /**
     * Get completable future.
     *
     * @return the completable future
     */
    @Bulkhead(name = "serviceA",  type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "serviceA", fallbackMethod = "fallbackA")
    @CircuitBreaker(name = "serviceA")
    public CompletableFuture<String> get(){
        log.info("Starting to execute a call for more than 2s");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CompletableFuture.completedFuture("success after 3s") ;
    }

    /**
     * Fallback a completable future.
     *
     * @param throwable the throwable
     * @return the completable future
     */
    public CompletableFuture<String> fallbackA(Throwable throwable){

        return CompletableFuture.completedFuture("fallback")  ;
    }


}
