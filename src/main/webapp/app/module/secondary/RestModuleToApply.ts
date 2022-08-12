import { ModuleToApply } from '../domain/ModuleToApply';

export interface RestModuleToApply {
  projectFolder: string;
  commit: boolean;
  properties: {};
}

export const toRestModuleToApply = (moduleToApply: ModuleToApply): RestModuleToApply => ({
  projectFolder: moduleToApply.projectFolder,
  commit: moduleToApply.commit,
  properties: Object.fromEntries(moduleToApply.properties),
});
