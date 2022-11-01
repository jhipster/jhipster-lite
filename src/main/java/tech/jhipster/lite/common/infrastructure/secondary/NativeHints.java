package tech.jhipster.lite.common.infrastructure.secondary;

import com.oracle.svm.core.posix.headers.darwin.*;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.internal.*;
import org.eclipse.jgit.lib.*;
import org.springframework.aot.hint.*;

public class NativeHints implements RuntimeHintsRegistrar {

  private final MemberCategory[] memberCategories = {MemberCategory.DECLARED_FIELDS, MemberCategory.INTROSPECT_DECLARED_METHODS, MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS};

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

    /*
    Mimics quarkus native configuration. See https://github.com/quarkiverse/quarkus-jgit/blob/main/deployment/src/main/java/io/quarkus/jgit/deployment/JGitProcessor.java
    Their calls to
    ReflectiveClassBuildItem(true, true,
                "org.eclipse.jgit.api.MergeCommand$FastForwardMode",

    means methods, fields and constructors
     */
    // Using declared fields, methods and constructors here, if we have it up and running we may be more strict to e.g. public fields only
    hints.reflection().registerType(MergeCommand.FastForwardMode.class, memberCategories)
      .registerType(MergeCommand.FastForwardMode.Merge.class, memberCategories)
      .registerType(JGitText.class, memberCategories)
      .registerType(CoreConfig.AutoCRLF.class, memberCategories)
      .registerType(CoreConfig.CheckStat.class, memberCategories)
      .registerType(CoreConfig.EOL.class, memberCategories)
      .registerType(CoreConfig.EolStreamType.class, memberCategories)
      .registerType(CoreConfig.HideDotFiles.class, memberCategories)
      .registerType(CoreConfig.SymLinks.class, memberCategories)
      .registerType(CoreConfig.LogRefUpdates.class, memberCategories);


    hints.resources().registerPattern("org.eclipse.jgit.internal.JGitText")
      .registerPattern("generator/**");

  }
}
