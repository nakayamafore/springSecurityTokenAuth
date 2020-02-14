package com.example.webapi.infrastructure.jooq.converter;

public class BooleanConverter
    implements org.jooq.Converter<Integer, Boolean> {
  private static final long serialVersionUID = 1L;

  @Override
  public Boolean from(final Integer databaseObject) {
    return databaseObject == 1 ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public Integer to(final Boolean userObject) {
    return userObject ? 1 : 0;
  }

  @Override
  public Class<Integer> fromType() {
    return Integer.class;
  }

  @Override
  public Class<Boolean> toType() {
    return Boolean.class;
  }

}
