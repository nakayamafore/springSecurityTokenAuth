package com.example.demo.controller;

import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.request.GreetingRequest;
import com.example.demo.model.response.GreetingResponse;

@RestController
@RequestMapping(path = "hello")
public class HelloController {

  @GetMapping
  public GreetingResponse greeting() {
    return GreetingResponse.builder().name("????")
        .greeting("hello world").time(LocalDateTime.now()).build();
  }

  @PostMapping
  public GreetingResponse postGreeting(
      @RequestBody @Validated final GreetingRequest greetingRequest) {
    return GreetingResponse.builder().name(greetingRequest.getName())
        .greeting(
            "How are you doing " + greetingRequest.getName() + "?")
        .time(LocalDateTime.now()).build();
  }

}
