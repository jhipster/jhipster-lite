package tech.jhipster.lite.module.domain.resource;

import java.util.Collection;
import java.util.Collections;
import tech.jhipster.lite.error.domain.Assert;

public record JHipsterLandscapeLevel(Collection<JHipsterLandscapeElement> elements) {
  public JHipsterLandscapeLevel(Collection<JHipsterLandscapeElement> elements) {
    Assert.notEmpty("elements", elements);

    this.elements = Collections.unmodifiableCollection(elements);
  }
}
