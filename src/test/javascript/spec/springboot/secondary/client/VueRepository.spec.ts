import { stubAxiosHttp } from '../../../http/AxiosHttpStub';
import { Project } from '@/springboot/domain/Project';
import { createProject } from '../../domain/Project.fixture';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import VueRepository from '@/springboot/secondary/client/VueRepository';

describe('VueRepository', () => {
  it('should add Vue', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const vueRepository = new VueRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    vueRepository.add(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/vue');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Vue with minimal style', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const vueRepository = new VueRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    vueRepository.addWithStyle(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/vue/styled');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
});
