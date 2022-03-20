import { ProjectToUpdate, toProject } from '@/springboot/primary/ProjectToUpdate';
import { Project } from '@/springboot/domain/Project';

describe('ProjectToUpdate', () => {
  it('should convert maximal ProjectToUpdate to Project', () => {
    const projectToUpdate: ProjectToUpdate = {
      folder: 'tmp/beer',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: '8080',
    };

    expect(toProject(projectToUpdate)).toEqual<Project>({
      folder: 'tmp/beer',
      baseName: 'beer',
      projectName: 'Beer Project',
      packageName: 'tech.jhipster.beer',
      serverPort: 8080,
    });
  });
  it('should convert minimal ProjectToUpdate to Project', () => {
    const projectToUpdate: ProjectToUpdate = {
      folder: 'tmp/beer',
    };

    expect(toProject(projectToUpdate)).toEqual<Project>({
      folder: 'tmp/beer',
    });
  });
});
