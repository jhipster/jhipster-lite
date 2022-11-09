package tech.jhipster.lite.module.infrastructure.secondary.git;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import java.io.File;
import tech.jhipster.lite.common.domain.Generated;

@TargetClass(className = "org.eclipse.jgit.util.FS")
@Generated
public final class FSSubstitution {

  @Substitute
  public File userHome() {
    return new File(System.getProperty("user.home")).getAbsoluteFile();
  }
}
