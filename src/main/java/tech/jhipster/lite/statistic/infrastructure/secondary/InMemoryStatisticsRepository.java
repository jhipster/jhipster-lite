package tech.jhipster.lite.statistic.infrastructure.secondary;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.statistic.domain.AppliedModule;
import tech.jhipster.lite.statistic.domain.Statistics;
import tech.jhipster.lite.statistic.domain.StatisticsRepository;

@Repository
@Profile("!cloud")
class InMemoryStatisticsRepository implements StatisticsRepository {

  private final Collection<AppliedModule> appliedModules = Collections.newSetFromMap(new ConcurrentHashMap<>());

  @Override
  public void save(AppliedModule moduleApplied) {
    Assert.notNull("moduleApplied", moduleApplied);

    appliedModules.add(moduleApplied);
  }

  @Override
  public Statistics get() {
    return new Statistics(appliedModules.size());
  }

  void clear() {
    appliedModules.clear();
  }
}
