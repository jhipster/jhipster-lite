package tech.jhipster.light;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

@UnitTest
class AnnotationArchTest {

  @Test
  void shouldHaveUnitTestAnnotation() {
    JavaClasses importedClasses = new ClassFileImporter()
      .importPackages("tech.jhipster.light")
      .that(are(not(equivalentTo(UnitTest.class)).and(not(equivalentTo(IntegrationTest.class)))));

    ArchRule rule = classes()
      .that()
      .resideInAnyPackage("tech.jhipster.light..")
      .and()
      .haveSimpleNameEndingWith("Test")
      .should()
      .beAnnotatedWith(UnitTest.class);

    rule.check(importedClasses);
  }

  @Test
  void shouldHaveIntegrationTestAnnotation() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("tech.jhipster.light");

    ArchRule rule = classes()
      .that()
      .resideInAnyPackage("tech.jhipster.light..")
      .and()
      .haveSimpleNameEndingWith("IT")
      .should()
      .beAnnotatedWith(IntegrationTest.class);

    rule.check(importedClasses);
  }
}
