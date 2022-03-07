import { RestProject, toRestProject } from '@/generator/secondary/RestProject';
import { Project } from '@/generator/domain/Project';

describe('RestProject', () => {
  it('should convert to Project', () => {
    const project: Project = {
      folder: 'folder',
    };

    expect(toRestProject(project)).toEqual<RestProject>({
      folder: 'folder',
    });
  });
});
