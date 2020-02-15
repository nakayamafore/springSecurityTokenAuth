package com.example.webapi.converter;

import org.jooq.Converter;

public class BooleanConverter implements Converter<Byte, Boolean> {
  private static final long serialVersionUID = 1L;

  @Override
  public Boolean from(final Byte databaseObject) {
    return databaseObject == 1 ? true : false;
  }

  @Override
  public Byte to(final Boolean userObject) {
    return userObject ? (byte) 1 : (byte) 0;
  }

  @Override
  public Class<Byte> fromType() {
    return Byte.class;
  }

  @Override
  public Class<Boolean> toType() {
    return Boolean.class;
  }


}
