import { RestProject, toRestProject } from '@/generator/secondary/RestProject';
import { Project } from '@/generator/domain/Project';
import { createProject } from '../domain/Project.fixture';

describe('RestProject', () => {
  it('should convert to Project', () => {
    const project: Project = createProject({
      folder: 'folder',
    });

    expect(toRestProject(project)).toEqual<RestProject>({
      folder: 'folder',
    });
  });
});
