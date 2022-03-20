import { Project } from '@/springboot/domain/Project';

export const createProject = (project?: Partial<Project>): Project => ({
  folder: 'folder/path',
  baseName: 'beer',
  projectName: 'Beer Project',
  packageName: 'tech.jhipster.beer',
  serverPort: 8080,
  ...project,
});
