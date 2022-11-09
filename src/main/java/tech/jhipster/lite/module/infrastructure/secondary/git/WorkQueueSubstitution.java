package tech.jhipster.lite.module.infrastructure.secondary.git;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import tech.jhipster.lite.common.domain.Generated;

@TargetClass(className = "org.eclipse.jgit.lib.internal.WorkQueue")
@Substitute
@Generated
public final class WorkQueueSubstitution {

  private WorkQueueSubstitution() {}

  private static final ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

  @Substitute
  public static ScheduledThreadPoolExecutor getExecutor() {
    return executor;
  }
}
