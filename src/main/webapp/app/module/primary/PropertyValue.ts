import { ModulePropertyValueType } from '../domain/ModuleProperties';

export const notEmpty = (value: ModulePropertyValueType | undefined): boolean => {
  return !empty(value);
};

export const empty = (value: ModulePropertyValueType | undefined): boolean => {
  if (value === undefined) {
    return true;
  }

  if (typeof value === 'string') {
    return value.trim().length === 0;
  }

  return false;
};
