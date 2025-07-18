Feature: Sonar

  Scenario: Should apply backend sonar module
    When I apply "sonarqube-java-backend" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "."
      | sonar-project.properties |
    Then I should have files in "documentation"
      | sonar.md |

  Scenario: Should apply frontend and backend sonar module
    When I apply "sonarqube-java-backend-and-frontend" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "."
      | sonar-project.properties |
    Then I should have files in "documentation"
      | sonar.md |
