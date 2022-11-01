package tech.jhipster.lite.common.infrastructure.secondary.jgit;

import com.oracle.svm.core.annotate.*;

import java.util.concurrent.*;

@TargetClass(className = "org.eclipse.jgit.lib.internal.WorkQueue")
@Substitute
public final class WorkQueueSubstitution {

  private static final ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors
    .newScheduledThreadPool(1);

  @Substitute
  public static ScheduledThreadPoolExecutor getExecutor() {
    return executor;
  }
}
