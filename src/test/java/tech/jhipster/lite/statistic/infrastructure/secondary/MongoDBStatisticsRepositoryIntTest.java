package tech.jhipster.lite.statistic.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.statistic.domain.AppliedModuleFixture.*;

import java.util.Collections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.statistic.domain.StatisticsRepository;

@IntegrationTest
@ActiveProfiles("cloud")
@Disabled("Slow test, not needed at every build")
class MongoDBStatisticsRepositoryIntTest {

  private static final long memoryInBytes = Math.round(1024 * 1024 * 1024 * 0.6);
  private static final long memorySwapInBytes = Math.round(1024 * 1024 * 1024 * 0.8);
  private static final long nanoCpu = Math.round(1_000_000_000L * 0.1);

  private static MongoDBContainer mongoDbContainer;

  @Autowired
  private StatisticsRepository statistics;

  @BeforeAll
  @SuppressWarnings("resource")
  static void startMongo() {
    // Not starting a mongo in every env since this takes a lot of time and is only needed for those tests

    mongoDbContainer =
      new MongoDBContainer(DockerImageName.parse("mongo:5.0.10"))
        .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"))
        .withCommand(
          """
            --wiredTigerCacheSizeGB 0.25 \
            --wiredTigerCollectionBlockCompressor none \
            --slowOpSampleRate 0 \
            --setParameter ttlMonitorEnabled=false \
            --setParameter diagnosticDataCollectionEnabled=false \
            --setParameter logicalSessionRefreshMillis=6000000 \
            --setParameter enableFlowControl=false \
            --setParameter oplogFetcherUsesExhaust=false \
            --setParameter disableResumableRangeDeleter=true \
            --setParameter enableShardedIndexConsistencyCheck=false \
            --setParameter enableFinerGrainedCatalogCacheRefresh=false \
            --setParameter readHedgingMode=off \
            --setParameter loadRoutingTableOnStartup=false \
            --setParameter rangeDeleterBatchDelayMS=2000000 \
            --setParameter skipShardingConfigurationChecks=true \
            --setParameter syncdelay=3600\
            """
        )
        .withCreateContainerCmdModifier(cmd ->
          cmd.getHostConfig().withMemory(memoryInBytes).withMemorySwap(memorySwapInBytes).withNanoCPUs(nanoCpu)
        );

    mongoDbContainer.start();

    System.setProperty("TEST_MONGODB_URI", mongoDbContainer.getReplicaSetUrl());
  }

  @AfterAll
  static void stopMongo() {
    mongoDbContainer.stop();
  }

  @Test
  void shouldGetStatisticsFromMongoInCloudEnvironment() {
    assertThat(statistics).isInstanceOf(MongoDBStatisticsRepository.class);
  }

  @Test
  void shouldUpdateAndGetStatistics() {
    statistics.save(appliedModule());

    assertThat(statistics.get().appliedModules()).isEqualTo(1);
  }
}
