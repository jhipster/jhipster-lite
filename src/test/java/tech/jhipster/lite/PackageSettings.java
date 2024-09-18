package tech.jhipster.lite;

import org.approvaltests.core.ApprovalFailureReporter;
import org.approvaltests.reporters.*;

/**
 * ApprovalTests.Java configuration.
 * */
public class PackageSettings {

  private static final String ApprovalBaseDirectory = "../resources";
  private static final ApprovalFailureReporter UseReporter = new FirstWorkingReporter(
    new AutoApproveWhenEmptyReporter(),
    new DiffReporter()
  );
}
