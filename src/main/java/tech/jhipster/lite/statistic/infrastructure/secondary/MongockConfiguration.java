package tech.jhipster.lite.statistic.infrastructure.secondary;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@WithMongo
@Configuration
@EnableMongock
@AutoConfigureAfter(MongoDBConfiguration.class)
class MongockConfiguration {}
