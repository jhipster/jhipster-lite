package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginSlug(String slug) {
  public GradlePluginSlug {
    Assert.notBlank("slug", slug);
  }

  public static Optional<GradlePluginSlug> of(String dependencySlug) {
    return Optional.ofNullable(dependencySlug).filter(StringUtils::isNotBlank).map(GradlePluginSlug::new);
  }

  public String get() {
    return slug();
  }
}
