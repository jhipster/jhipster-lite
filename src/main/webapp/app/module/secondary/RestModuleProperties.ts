import { ModuleProperty } from '../domain/ModuleProperty';
import { RestModuleProperty, toProperty } from './RestModuleProperty';

export interface RestModuleProperties {
  definitions: RestModuleProperty[];
}

export const toProperties = (restProperties: RestModuleProperties | undefined): ModuleProperty[] => {
  if (!restProperties) {
    return [];
  }

  return restProperties.definitions.map(toProperty);
};
