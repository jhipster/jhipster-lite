Feature: Angular health module

  Scenario: Should apply Angular health module    
    When I apply legacy modules to default project
      | /api/inits/full                         |
      | /api/clients/angular                    |
      | /api/clients/angular/admin-pages/health |
    Then I should have files in "src/main/webapp/app/admin/health/modal"
      | health-modal.component.css |
