package tech.jhipster.lite.statistic.infrastructure.secondary;

import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import tech.jhipster.lite.common.domain.Generated;

@Configuration
@Profile("cloud")
@EnableMongoRepositories
@Import({ MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@Generated(reason = "Not testing it since starting a mongo cost a lot of time")
class MongoDBConfiguration {}
