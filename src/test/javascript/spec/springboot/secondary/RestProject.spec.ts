import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { Project } from '@/springboot/domain/Project';
import { createProject } from '../domain/Project.fixture';
import { toRestGeneratorJHipster } from '@/springboot/secondary/RestGeneratorJHipster';

describe('RestProject', () => {
  it('should convert to Project', () => {
    const project: Project = createProject({
      folder: 'folder',
    });

    expect(toRestProject(project)).toEqual<RestProject>({
      folder: 'folder',
      'generator-jhipster': toRestGeneratorJHipster(project),
    });
  });
});
