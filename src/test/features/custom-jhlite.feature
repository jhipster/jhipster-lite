Feature: Custom JHLite module

  Scenario: Should apply custom jhlite module
    When I apply modules to default project
      | maven-java    |
      | spring-boot   |
      | custom-jhlite |
    Then I should have "@SpringBootApplication(scanBasePackageClasses = { JHLiteApp.class, ChipsApp.class })" in "src/main/java/tech/jhipster/chips/ChipsApp.java"
