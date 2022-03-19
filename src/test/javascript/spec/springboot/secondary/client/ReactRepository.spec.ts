import { stubAxiosHttp } from '../../../http/AxiosHttpStub';
import { Project } from '@/springboot/domain/Project';
import { createProject } from '../../domain/Project.fixture';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import ReactRepository from '@/springboot/secondary/client/ReactRepository';

describe('ReactRepository', () => {
  it('should add React', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const reactRepository = new ReactRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    reactRepository.add(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/react');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add React with minimal style', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const reactRepository = new ReactRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    reactRepository.addWithStyle(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/react/styled');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
});
