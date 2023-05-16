package com.srini.timelimiter.service;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AppService {


    @TimeLimiter(name = "serviceA", fallbackMethod = "fallbackA")
    public CompletableFuture<String> get(){
        log.info("Starting to execute a call for more than 2s");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CompletableFuture.completedFuture("success after 3s") ;
    }

    public CompletableFuture<String> fallbackA(Throwable throwable){

        return CompletableFuture.completedFuture("fallback")  ;
    }


}
