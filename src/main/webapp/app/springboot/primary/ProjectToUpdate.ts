import { Project } from '@/springboot/domain/Project';

export interface ProjectToUpdate {
  folder: string;
  baseName?: string;
  projectName?: string;
  packageName?: string;
  serverPort?: string;
}

export const toProject = (projectToUpdate: ProjectToUpdate): Project => ({
  folder: projectToUpdate.folder,
  baseName: projectToUpdate.baseName,
  projectName: projectToUpdate.projectName,
  packageName: projectToUpdate.packageName,
  serverPort: toOptionalInteger(projectToUpdate.serverPort),
});

const toOptionalInteger = (numberToConvert?: string): number | undefined => {
  return numberToConvert ? parseInt(numberToConvert) : undefined;
};
