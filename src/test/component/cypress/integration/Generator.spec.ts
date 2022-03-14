import { composeSelector, dataSelector } from '../support/selector';

const generatorSelector = (name: string) => dataSelector(composeSelector('generator', name));

describe('Generator', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should redirect to generator by default', () => {
    cy.url().should('include', '/generator');
  });

  it('should display generator page', () => {
    cy.get(generatorSelector('title')).contains('JHipster Lite');
    cy.get(generatorSelector('subtitle')).contains('Spring Boot Generator');

    cy.get(generatorSelector('init-button')).contains('Init');
    cy.get(generatorSelector('add-maven-button')).contains('Maven');
    cy.get(generatorSelector('add-java-base-button')).contains('Java Base');
    cy.get(generatorSelector('add-spring-boot-button')).contains('Spring Boot');
    cy.get(generatorSelector('add-spring-boot-mvc-tomcat-button')).contains('Spring MVC Tomcat');
    cy.get(generatorSelector('add-angular-button')).contains('Angular');
    cy.get(generatorSelector('add-react-button')).contains('React');
    cy.get(generatorSelector('add-vue-button')).contains('Vue');
    cy.get(generatorSelector('add-frontend-maven-plugin-button')).contains('Frontend Maven Plugin');
    cy.get(generatorSelector('add-banner-button')).contains('Spring-boot Banner');
    cy.get(generatorSelector('add-banner-ippon-button')).contains('Ippon Banner');
    cy.get(generatorSelector('add-banner-v2-button')).contains('Jhipster-2-banner');
    cy.get(generatorSelector('add-banner-v3-button')).contains('Jhipster-3-banner');
    cy.get(generatorSelector('add-banner-v7-button')).contains('Jhipster-7-banner');
    cy.get(generatorSelector('add-banner-v7-react-button')).contains('Jhipster-7-react-banner');
    cy.get(generatorSelector('add-banner-v7-vue-button')).contains('Jhipster-7-vue-banner');
  });
});
