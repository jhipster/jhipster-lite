Feature: React JWT

  Scenario: Should apply react jwt module to styled react
    When I apply legacy modules to default project
      | /api/inits/full           |
      | /api/clients/react/styles |
      | /api/clients/react/jwt    |
    Then I should have files in "src/main/webapp/app/common/services"
      | storage.ts |
