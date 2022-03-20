export const dataSelector = (value: string) => `[data-selector="${value}"]`;

export const composeSelector = (name: string, ...values: string[]): string =>
  [name, ...values].filter(value => value !== undefined).join('.');
