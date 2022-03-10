import { Project } from '@/springboot/domain/Project';
import { RestGeneratorJHipster, toRestGeneratorJHipster } from './RestGeneratorJHipster';

export interface RestProject {
  folder: string;
  'generator-jhipster': RestGeneratorJHipster;
}

export const toRestProject = (project: Project): RestProject => ({
  folder: project.folder,
  'generator-jhipster': toRestGeneratorJHipster(project),
});
