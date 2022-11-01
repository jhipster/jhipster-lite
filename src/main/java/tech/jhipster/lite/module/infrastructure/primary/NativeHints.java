package tech.jhipster.lite.module.infrastructure.primary;

import org.springframework.aot.hint.*;

public class NativeHints implements RuntimeHintsRegistrar {
  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

    hints.reflection().registerType(RestJHipsterModuleProperties.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS)
      .registerType(RestJHipsterLandscape.class, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS, MemberCategory.INTROSPECT_DECLARED_METHODS);

  }
}
