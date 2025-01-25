Feature: JPA modules

  Scenario: Should add PostgresSQL elements using legacy url
    When I apply modules to default project
      | maven-java     |
      | spring-boot    |
      | jpa-postgresql |
    Then I should have files in ""
      | pom.xml |
    And I should have files in "documentation"
      | postgresql.md |
    And I should have files in "src/main/docker"
      | postgresql.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/wire/postgresql/infrastructure/secondary"
      | DatabaseConfiguration.java |
    And I should have files in "src/main/resources/config"
      | application.yml |
    And I should have files in "src/test/resources/config"
      | application-test.yml |

  Scenario: Should get PostgresSQL module properties definition
    When I get module "jpa-postgresql" properties definition
    Then I should have properties definitions
      | Key                       | Type    | Mandatory |
      | packageName               | STRING  | true      |
      | baseName                  | STRING  | true      |
      | indentSize                | INTEGER | false     |
      | springConfigurationFormat | STRING  | false     |

  Scenario: Should add MariaDB elements using legacy url
    When I apply modules to default project
      | maven-java  |
      | spring-boot |
      | jpa-mariadb |
    Then I should have files in ""
      | pom.xml |
    And I should have files in "documentation"
      | mariadb.md |
    And I should have files in "src/main/docker"
      | mariadb.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/wire/mariadb/infrastructure/secondary"
      | DatabaseConfiguration.java |
    And I should have files in "src/main/resources/config"
      | application.yml |
    And I should have files in "src/test/resources/config"
      | application-test.yml |

  Scenario: Should get MariaDB module properties definition
    When I get module "jpa-mariadb" properties definition
    Then I should have properties definitions
      | Key                       | Type    | Mandatory |
      | packageName               | STRING  | true      |
      | baseName                  | STRING  | true      |
      | indentSize                | INTEGER | false     |
      | springConfigurationFormat | STRING  | false     |

  Scenario: Should add MySQL elements using legacy url
    When I apply modules to default project
      | maven-java  |
      | spring-boot |
      | jpa-mysql   |
    Then I should have files in ""
      | pom.xml |
    And I should have files in "documentation"
      | mysql.md |
    And I should have files in "src/main/docker"
      | mysql.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/wire/mysql/infrastructure/secondary"
      | DatabaseConfiguration.java |
    And I should have files in "src/main/resources/config"
      | application.yml |
    And I should have files in "src/test/resources/config"
      | application-test.yml |

  Scenario: Should get MySQL module properties definition
    When I get module "jpa-mysql" properties definition
    Then I should have properties definitions
      | Key                       | Type    | Mandatory |
      | packageName               | STRING  | true      |
      | baseName                  | STRING  | true      |
      | indentSize                | INTEGER | false     |
      | springConfigurationFormat | STRING  | false     |

  Scenario: Should add MsSQL elements using legacy url
    When I apply modules to default project
      | maven-java  |
      | spring-boot |
      | jpa-mssql   |
    Then I should have files in ""
      | pom.xml |
    And I should have files in "documentation"
      | mssql.md |
    And I should have files in "src/main/docker"
      | mssql.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/wire/mssql/infrastructure/secondary"
      | DatabaseConfiguration.java |
    And I should have files in "src/test/java/tech/jhipster/chips"
      | MsSQLTestContainerExtension.java |
    And I should have files in "src/test/resources"
      | container-license-acceptance.txt |
    And I should have files in "src/main/resources/config"
      | application.yml |
    And I should have files in "src/test/resources/config"
      | application-test.yml |

  Scenario: Should get MsSQL module properties definition
    When I get module "jpa-mssql" properties definition
    Then I should have properties definitions
      | Key                       | Type    | Mandatory |
      | packageName               | STRING  | true      |
      | baseName                  | STRING  | true      |
      | indentSize                | INTEGER | false     |
      | springConfigurationFormat | STRING  | false     |
