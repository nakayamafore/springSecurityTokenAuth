package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "admin")
@AllArgsConstructor
@Slf4j
public class AdminController {

  private final UserService userService;

  @GetMapping
  public String greeting(
      @AuthenticationPrincipal(expression = "user") final User user) {
    log.info("access user : {}", user.toString());
    return "hello admin " + user.getName();
  }

  @GetMapping(path = "{name}")
  public String greeting(
      @AuthenticationPrincipal(expression = "user") final User user,
      @PathVariable(name = "name") final String name) {
    log.info("access user : {}", user.toString());
    return userService.findByName(name)
        .map(u -> "hello " + u.getName()).orElse("unknown user");
  }
}
