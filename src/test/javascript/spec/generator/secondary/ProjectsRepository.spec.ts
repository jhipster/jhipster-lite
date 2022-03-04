import { Project } from '@/generator/domain/Project';
import ProjectRepository from '@/generator/secondary/ProjectRepository';
import { stubAxiosHttp } from '../../http/AxiosHttpStub';

describe('ProjectsRepository', () => {
  it('Should init project', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const projectRepository = new ProjectRepository(axiosHttpStub);
    const project: Project = { folder: 'folder/path' };

    projectRepository.init(project);

    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/projects/init');
    expect(payload).toEqual<Project>(project);
  });
});
