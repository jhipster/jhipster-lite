import { createProject } from '../domain/Project.fixture';
import { RestGeneratorJHipster, toRestGeneratorJHipster } from '@/springboot/secondary/RestGeneratorJHipster';
import { Project } from '@/springboot/domain/Project';

describe('RestGeneratorJHipster', () => {
  it('should convert maximal Project to RestGeneratorJHipster', () => {
    const project = createProject({
      baseName: 'chips',
      projectName: 'Chips Project',
      packageName: 'tech.jhipster.chips',
      serverPort: 9000,
    });

    expect(toRestGeneratorJHipster(project)).toEqual<RestGeneratorJHipster>({
      baseName: 'chips',
      projectName: 'Chips Project',
      packageName: 'tech.jhipster.chips',
      serverPort: 9000,
    });
  });

  it('should convert minimal Project to RestGeneratorJHipster', () => {
    const project: Project = createProject({
      baseName: undefined,
      projectName: undefined,
      packageName: undefined,
      serverPort: undefined,
    });

    expect(toRestGeneratorJHipster(project)).toEqual<RestGeneratorJHipster>({});
  });
});
