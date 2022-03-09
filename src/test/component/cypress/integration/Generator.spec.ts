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
    cy.get(generatorSelector('title')).contains('Generator');

    cy.get(generatorSelector('init-button')).contains('Init');
    cy.get(generatorSelector('add-maven-button')).contains('Maven');
    cy.get(generatorSelector('add-java-base-button')).contains('JavaBase');
    cy.get(generatorSelector('add-spring-boot-button')).contains('SpringBoot');
  });
});
