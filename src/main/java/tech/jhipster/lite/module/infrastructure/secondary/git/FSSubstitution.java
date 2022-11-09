package tech.jhipster.lite.module.infrastructure.secondary.git;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import tech.jhipster.lite.common.domain.Generated;

import java.io.File;

@TargetClass(className = "org.eclipse.jgit.util.FS")
@Generated
public final class FSSubstitution {

  @Substitute
  public File userHome() {
    return new File(System.getProperty("user.home")).getAbsoluteFile();
  }
}
