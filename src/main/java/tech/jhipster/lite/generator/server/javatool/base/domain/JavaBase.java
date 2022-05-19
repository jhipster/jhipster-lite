package tech.jhipster.lite.generator.server.javatool.base.domain;

import java.util.List;

public class JavaBase {

  private JavaBase() {}

  public static List<String> errorDomainFiles() {
    return List.of(
      "Assert.java",
      "MissingMandatoryValueException.java",
      "AssertionException.java",
      "NotAfterTimeException.java",
      "NotBeforeTimeException.java",
      "NullElementInCollectionException.java",
      "NumberValueTooHighException.java",
      "NumberValueTooLowException.java",
      "StringTooLongException.java",
      "StringTooShortException.java",
      "TooManyElementsException.java"
    );
  }

  public static List<String> errorDomainTestFiles() {
    return List.of("AssertTest.java", "MissingMandatoryValueExceptionTest.java");
  }

  public static List<String> annotationsFiles() {
    return List.of("UnitTest.java", "ReplaceCamelCase.java");
  }
}
