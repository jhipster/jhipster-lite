import { Project } from '@/generator/domain/Project';

export const createProject = (project?: Partial<Project>): Project => ({
  folder: 'folder/path',
  ...project,
});
