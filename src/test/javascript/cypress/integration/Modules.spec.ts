import { dataSelector } from '../support/selector';

describe('Modules', () => {
  it('Should display modules', () => {
    cy.visit('/modules');

    cy.get(dataSelector('modules-list')).should('be.visible');
  });
});
