Feature: Java base

  Scenario: Should add java base
    When I add Java base to default project
    Then I should have files in "src/main/java/tech/jhipster/chips/error/domain"
      | Assert.java |
    And I should have history entry for "java-base"
