Feature: Vue i18n

  Scenario: Should apply vue i18n module to vue
    When I apply modules to default project
      | init        |
      | vue-core    |
      | vue-i18next |
    Then I should have files in "src/main/webapp/app"
      | i18n.ts         |
      | Translations.ts |
    And I should have files in "src/main/webapp/app/common/"
      | CommonTranslations.ts |
    And I should have files in "src/main/webapp/app/common/locales"
      | fr.ts |
      | en.ts |
    And I should have files in "src/test"
      | setupTests.ts |
