package tech.jhipster.lite.statistic.infrastructure.secondary;

import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("cloud")
interface SpringDataMongoDbStatisticsRepository extends CrudRepository<AppliedModuleDocument, UUID> {}
