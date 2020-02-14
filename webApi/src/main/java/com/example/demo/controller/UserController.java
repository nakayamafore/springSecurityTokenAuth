package com.example.demo.controller;

import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "user")
@Slf4j
public class UserController {

  @GetMapping
  public String greeting(
      @AuthenticationPrincipal(expression = "user") final User user) {
    log.info("access user : {}", user.toString());
    return "hello " + user.getName();
  }

  @GetMapping(path = "echo/{message}")
  public String getEcho(
      @PathVariable(name = "message") final String message) {
    return message.toUpperCase();
  }

  @PostMapping(path = "echo")
  public String postEcho(
      @RequestBody final Map<String, String> message) {
    return message.toString();
  }

}
