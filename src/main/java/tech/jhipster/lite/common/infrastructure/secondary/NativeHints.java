package tech.jhipster.lite.common.infrastructure.secondary;

import com.oracle.svm.core.posix.headers.darwin.*;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.internal.*;
import org.eclipse.jgit.lib.*;
import org.springframework.aot.hint.*;

public class NativeHints implements RuntimeHintsRegistrar {

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
    hints
      .reflection()
      .registerType(MergeCommand.FastForwardMode.class, MemberCategory.values())
      .registerType(MergeCommand.FastForwardMode.Merge.class, MemberCategory.values())
      .registerType(JGitText.class, MemberCategory.values())
      .registerType(CoreConfig.AutoCRLF.class, MemberCategory.values())
      .registerType(CoreConfig.CheckStat.class, MemberCategory.values())
      .registerType(CoreConfig.EOL.class, MemberCategory.values())
      .registerType(CoreConfig.EolStreamType.class, MemberCategory.values())
      .registerType(CoreConfig.HideDotFiles.class, MemberCategory.values())
      .registerType(CoreConfig.SymLinks.class, MemberCategory.values())
      .registerType(CoreConfig.LogRefUpdates.class, MemberCategory.values());

    // Needed for xml reading and writing via dom4j
    hints
      .reflection()
      .registerType(TypeReference.of("org.dom4j.util.ProxyDocumentFactory"), MemberCategory.values())
      .registerType(TypeReference.of("org.dom4j.DocumentFactory"), MemberCategory.values())
      .registerType(TypeReference.of("org.dom4j.dom.NonLazyDocumentFactory"), MemberCategory.values());

    // internal usage
    hints.reflection().registerType(TypeReference.of("com.sun.org.apache.xpath.internal.functions.FuncLocalPart"), MemberCategory.values());

    // patterns for JGit and our own template files
    hints.resources().registerPattern("org.eclipse.jgit.internal.JGitText").registerPattern("generator/**");
  }
}
