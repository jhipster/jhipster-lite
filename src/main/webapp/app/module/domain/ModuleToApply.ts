export type ProjectFolder = string;
export type ModulePropertiesToApply = Map<string, string | number | boolean>;

export interface ModuleToApply {
  projectFolder: ProjectFolder;
  properties: ModulePropertiesToApply;
}
