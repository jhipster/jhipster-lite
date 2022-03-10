import { ProjectToUpdate } from '@/springboot/primary/ProjectToUpdate';

export const createProjectToUpdate = (projectToUpdate?: Partial<ProjectToUpdate>): ProjectToUpdate => ({
  folder: 'folder/path',
  baseName: 'beer',
  projectName: 'Beer Project',
  packageName: 'tech.jhipster.beer',
  serverPort: '8080',
  ...projectToUpdate,
});
