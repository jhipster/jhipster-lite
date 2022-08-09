import { ModuleProperty, ModulePropertyType } from '../domain/ModuleProperty';

export interface RestModuleProperty {
  type: ModulePropertyType;
  mandatory: boolean;
  key: string;
  description?: string;
  example?: string;
}

export const toProperty = (restProperty: RestModuleProperty): ModuleProperty => ({
  type: restProperty.type,
  mandatory: restProperty.mandatory,
  key: restProperty.key,
  description: restProperty.description,
  example: restProperty.example,
});
