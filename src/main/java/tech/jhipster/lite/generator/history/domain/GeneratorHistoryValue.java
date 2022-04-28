package tech.jhipster.lite.generator.history.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import java.time.Instant;

public record GeneratorHistoryValue(
  String serviceId,

  @JsonSerialize(using = InstantSerializer.class)
  @JsonDeserialize(using = DefaultInstantDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  Instant timestamp
) {}
