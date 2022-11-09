package tech.jhipster.lite.project.infrastructure.secondary;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import tech.jhipster.lite.project.domain.history.ProjectAction;
import tech.jhipster.lite.project.domain.history.ProjectHistory;

class NativeHints implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    hints
      .reflection()
      .registerType(PersistedProjectHistory.class, MemberCategory.values())
      .registerType(ProjectHistory.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS)
      .registerType(ProjectAction.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS)
      .registerType(PersistedProjectAction.class, MemberCategory.values());
  }
}
