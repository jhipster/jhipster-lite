package tech.jhipster.lite.dsl.parser.infrastructure.secondary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.dsl.common.domain.DslProperties;
import tech.jhipster.lite.dsl.parser.domain.*;
import tech.jhipster.lite.dsl.parser.domain.clazz.DslClass;
import tech.jhipster.lite.error.domain.Assert;

@UnitTest
class FileSystemDslRepositoryTest {

  @Test
  void shouldCreateAValidDslApp() {
    FileSystemDslRepository fileSytemDslRepository = new FileSystemDslRepository();
    DslProperties properties = new DslProperties("/tmp/jhipster", false);
    DslApplication dslApp = fileSytemDslRepository.parseDsl(new JhipsterDslFileIdentifiant("dsl/jhlite-all.dsl"), properties);

    assertNotNull(dslApp);
    assertNotNull(dslApp.getConfig());
    assertNotNull(dslApp.getLstDslContext());
    //displayDslApp(dslApp);
  }

  @Test
  void shouldGenerateDslApp() {
    FileSystemDslRepository fileSytemDslRepository = new FileSystemDslRepository();

    JhipsterDslFileToSave fileToSave = new JhipsterDslFileToSave(
      JhipsterDslFileToImport.builder().originalFilename("test").bytes(new byte[] {}).name("test").build()
    );
    JhipsterDslFileIdentifiant fileID = fileSytemDslRepository.createDslFile(fileToSave);

    assertNotNull(fileID);
    //displayDslApp(dslApp);
  }

  private void displayDslApp(DslApplication dslApp) {
    Assert.notNull("dslApp", dslApp);

    StringBuilder sb = new StringBuilder("");
    displayConfig(dslApp, sb);
    displayContexts(dslApp, sb);
    System.out.println(sb.toString());
  }

  private static void displayConfig(DslApplication dslApp, StringBuilder sb) {
    sb
      .append("config: \n")
      .append("UseFluentMethod=")
      .append(dslApp.getConfig().getFluentMethod().get())
      .append("\n")
      .append("BaseName=")
      .append(dslApp.getConfig().getBaseName().get())
      .append("\n")
      .append("BasePackage=")
      .append(dslApp.getConfig().getBasePackage().get())
      .append("\n")
      .append("PackageDomainName=")
      .append(dslApp.getConfig().getPackageDomainName().get())
      .append("\n")
      .append("PackageInfrastructureName=")
      .append(dslApp.getConfig().getPackageInfrastructureName().get())
      .append("\n")
      .append("PackageInfrastructurePrimaryName=")
      .append(dslApp.getConfig().getPackageInfrastructurePrimaryName().get())
      .append("\n")
      .append("PackageInfrastructureSecondaryName=")
      .append(dslApp.getConfig().getPackageInfrastructureSecondaryName().get())
      .append("\n")
      .append("getBasePackage=")
      .append(dslApp.getConfig().getBasePackage().get())
      .append("\n");
  }

  private static void displayContexts(DslApplication dslApp, StringBuilder sb) {
    sb.append("Contexts: \n");
    dslApp.getLstDslContext().forEach(ctx -> displayContext(ctx, sb));
  }

  private static void displayContext(DslContext dslCtx, StringBuilder sb) {
    sb.append("Context ").append(dslCtx.getName().get()).append(" :\n");
    displayDomain(dslCtx.getDomain(), sb);
  }

  private static void displayDomain(DslDomain dslDomain, StringBuilder sb) {
    sb.append("Domain :\n");
    dslDomain.getDslClasses().forEach(ctx -> displayDslClass(ctx, sb));
  }

  private static void displayDslClass(DslClass dslClass, StringBuilder sb) {
    sb.append("\nClass key : ").append(dslClass.getName().get());
    sb.append("\nClass type : ").append(dslClass.getType().key());
    if (dslClass.getComment().isPresent()) {
      sb.append("\nClass Comment : ").append(dslClass.getComment().get().get()).append("\n");
    }
    sb.append("\nClass field : \n");
    sb.append(dslClass.getType().key()).append(" ").append(dslClass.getName().get()).append(" :\n");
    dslClass.getFields().stream().forEach(ctx -> sb.append(ctx).append("\n"));
  }
}
