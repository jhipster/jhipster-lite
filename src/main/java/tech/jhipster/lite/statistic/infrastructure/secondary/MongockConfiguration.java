package tech.jhipster.lite.statistic.infrastructure.secondary;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.common.domain.Generated;

@WithMongo
@Configuration
@EnableMongock
@AutoConfigureAfter(MongoDBConfiguration.class)
@Generated(reason = "Not testing it since starting a mongo cost a lot of time")
class MongockConfiguration {}
