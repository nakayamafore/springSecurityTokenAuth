package com.example.demo.repository.impl;

import static com.example.webapi.infrastructure.jooq.tables.User.USER;
import java.util.Optional;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final DSLContext jooq;

  @Override
  public Optional<User> findByEmail(final String email) {
    return Optional.of(jooq.select().from(USER)
        .where(USER.EMAIL.eq(email)).fetchOne().into(User.class));
  }

  @Override
  public Optional<User> findFirstByName(final String name) {
    return Optional.of(jooq.select().from(USER)
        .where(USER.NAME.eq(name)).fetchOne().into(User.class));
  }

  @Override
  public Optional<User> findById(final Long id) {
    return Optional.of(jooq.select().from(USER).where(USER.ID.eq(id))
        .fetchOne().into(User.class));
  }

}
