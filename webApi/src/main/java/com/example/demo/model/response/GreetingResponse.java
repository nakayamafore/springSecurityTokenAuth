package com.example.demo.model.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class GreetingResponse {

  private String name;
  private String greeting;
  private LocalDateTime time;

  private GreetingResponse(final String name, final String greeting,
      final LocalDateTime time) {
    this.name = name;
    this.greeting = greeting;
    this.time = time;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private String greeting;
    private LocalDateTime time;

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder greeting(final String greeting) {
      this.greeting = greeting;
      return this;
    }

    public Builder time(final LocalDateTime time) {
      this.time = time;
      return this;
    }

    public GreetingResponse build() {
      return new GreetingResponse(name, greeting, time);
    }
  }

}
