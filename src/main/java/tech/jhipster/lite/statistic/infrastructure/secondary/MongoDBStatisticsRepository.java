package tech.jhipster.lite.statistic.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.statistic.domain.AppliedModule;
import tech.jhipster.lite.statistic.domain.Statistics;
import tech.jhipster.lite.statistic.domain.StatisticsRepository;

@WithMongo
@Repository
@Generated(reason = "Starting a mongo takes a lot of time so tests for this are basically disabled")
class MongoDBStatisticsRepository implements StatisticsRepository {

  private final SpringDataMongoDbStatisticsRepository statistics;

  MongoDBStatisticsRepository(SpringDataMongoDbStatisticsRepository statistics) {
    this.statistics = statistics;
  }

  @Override
  public void save(AppliedModule moduleApplied) {
    Assert.notNull("moduleApplied", moduleApplied);

    statistics.save(AppliedModuleDocument.from(moduleApplied));
  }

  @Override
  public Statistics get() {
    return new Statistics(statistics.count());
  }
}
