package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.aot.hint.*;
import tech.jhipster.lite.project.domain.history.*;

class NativeHints implements RuntimeHintsRegistrar {
  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

    hints.reflection()
      .registerType(PersistedProjectHistory.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS)
      .registerType(ProjectHistory.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS)
      .registerType(PersistedProjectAction.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS);
  }
}
