package com.jhipster.test.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class CucumberJpaReset {

  @Autowired
  private Collection<JpaRepository<?, ?>> repositories;

  @After
  @Before
  public void wipeData() {
    repositories.forEach(JpaRepository::deleteAllInBatch);
  }
}
