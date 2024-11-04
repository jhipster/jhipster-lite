export const dataSelector = (selector: string): string => `[data-cy="${selector}"], [data-test="${selector}"], [data-testid="${selector}"]`;
