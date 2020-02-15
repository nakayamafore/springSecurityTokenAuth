package com.example.demo.model.request;

import lombok.Data;

@Data
public class WebApiAuthRequest {
  private String email;
  private String pass;
}
