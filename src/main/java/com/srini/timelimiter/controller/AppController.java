package com.srini.timelimiter.controller;

import com.srini.timelimiter.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService ;


    @GetMapping("/timelimit")
    public String get() throws ExecutionException, InterruptedException {
        return appService.get().get();
    }

}
