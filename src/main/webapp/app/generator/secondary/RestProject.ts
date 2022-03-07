import { Project } from '@/generator/domain/Project';

export interface RestProject {
  folder: string;
}

export const toRestProject = (project: Project): RestProject => ({
  folder: project.folder,
});
