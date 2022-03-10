import { Project } from '@/springboot/domain/Project';

export interface RestGeneratorJHipster {
  baseName?: string;
  projectName?: string;
  packageName?: string;
  serverPort?: number;
}

export const toRestGeneratorJHipster = (project: Project): RestGeneratorJHipster => ({
  baseName: project.baseName,
  projectName: project.projectName,
  packageName: project.packageName,
  serverPort: project.serverPort,
});
