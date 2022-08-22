package tech.jhipster.lite.statistic.infrastructure.secondary;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@WithMongo
@Repository
interface SpringDataMongoDbStatisticsRepository extends CrudRepository<AppliedModuleDocument, UUID> {}
