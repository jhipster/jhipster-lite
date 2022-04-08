import { stubAxiosHttp } from '../../../http/AxiosHttpStub';
import { Project } from '@/springboot/domain/Project';
import { createProject } from '../../domain/Project.fixture';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import AngularRepository from '@/springboot/secondary/client/AngularRepository';

describe('AngularRepository', () => {
  it('should add Angular', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const angularRepository = new AngularRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    angularRepository.add(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/clients/angular');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Angular with minimal style', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const angularRepository = new AngularRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    angularRepository.addWithStyle(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/clients/angular/styles');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
});
