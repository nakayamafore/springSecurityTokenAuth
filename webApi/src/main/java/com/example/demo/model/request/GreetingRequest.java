package com.example.demo.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GreetingRequest {
  @NotBlank
  private String name;
  @NotNull
  private String greeting;
}
