package tech.jhipster.forge;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

@UnitTest
public class HexagonalArchTest {

  @Test
  void domainShouldNotInteractWithOutside() {
    JavaClasses importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("tech.jhipster.forge");

    noClasses()
      .that()
      .resideInAnyPackage("..domain..")
      .should()
      .dependOnClassesThat()
      .resideInAnyPackage("..application..", "..infrastructure..", "..primary..", "..secondary..")
      .because("Domain should not interact with application, infrastructure, primary or secondary")
      .check(importedClasses);
  }

  @Test
  void applicationShouldNotInteractWithSecondary() {
    JavaClasses importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("tech.jhipster.forge");

    noClasses()
      .that()
      .resideInAnyPackage("..application..")
      .should()
      .dependOnClassesThat()
      .resideInAnyPackage("..secondary..")
      .because("Application should not interact with secondary")
      .check(importedClasses);
  }

  @Test
  void primaryShouldNotInteractWithSecondary() {
    JavaClasses importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("tech.jhipster.forge");

    noClasses()
      .that()
      .resideInAnyPackage("..primary..")
      .should()
      .dependOnClassesThat()
      .resideInAnyPackage("..secondary..")
      .because("Primary should not interact with secondary")
      .check(importedClasses);
  }
}
