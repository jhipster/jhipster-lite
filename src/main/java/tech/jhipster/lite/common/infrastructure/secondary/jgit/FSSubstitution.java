package tech.jhipster.lite.common.infrastructure.secondary.jgit;

import com.oracle.svm.core.annotate.*;
import java.io.*;

@TargetClass(className = "org.eclipse.jgit.util.FS")
public final class FSSubstitution {

  @Substitute
  public File userHome() {
    return new File(System.getProperty("user.home")).getAbsoluteFile();
  }
}
