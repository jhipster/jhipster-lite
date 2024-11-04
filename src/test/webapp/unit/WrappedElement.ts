export const wrappedElement = (selector: string) => `[data-cy="${selector}"], [data-test="${selector}"], [data-testid="${selector}"]`;
