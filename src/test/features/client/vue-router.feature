Feature: Vue router

  Scenario: Should apply Vue Router module
    Given I apply "vue-core" module to default project with package json without parameters
    When I apply "vue-router" module without parameters to last project
    Then I should have "<router-view />" in "src/main/webapp/app/AppVue.vue"
