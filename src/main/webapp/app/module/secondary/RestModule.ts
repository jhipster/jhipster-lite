import { Module } from '../domain/Module';
import { RestModuleProperties, toProperties } from './RestModuleProperties';

export interface RestModule {
  slug: string;
  description: string;
  properties?: RestModuleProperties;
  tags?: string[];
}

export const toModule = (restModule: RestModule): Module => ({
  slug: restModule.slug,
  description: restModule.description,
  properties: toProperties(restModule.properties),
  tags: toTags(restModule.tags),
});

const toTags = (tags: string[] | undefined): string[] => {
  if (tags === undefined) {
    return [];
  }

  return tags;
};
