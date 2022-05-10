import { stubAxiosHttp } from '../../../http/AxiosHttpStub';
import { Project } from '../../../../../../main/webapp/app/springboot/domain/Project';
import { createProject } from '../../domain/Project.fixture';
import { RestProject, toRestProject } from '../../../../../../main/webapp/app/springboot/secondary/RestProject';
import SvelteRepository from '../../../../../../main/webapp/app/springboot/secondary/client/SvelteRepository';
import { stubProjectHistoryService } from '../../../common/domain/ProjectHistoryService.fixture';

const PROJECT_FOLDER = 'folder/path';

describe('SvelteRepository', function () {
  it('should add Svelte', async function () {
    const axiosHttpStub = stubAxiosHttp();
    const svelteRepository = new SvelteRepository(axiosHttpStub, stubProjectHistoryService());
    const project: Project = createProject({ folder: PROJECT_FOLDER });

    await svelteRepository.add(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/clients/svelte');
    expect(payload).toEqual(expectedRestProject);
  });

  it('should add Svelte with style', async function () {
    const axiosHttpStub = stubAxiosHttp();
    const svelteRepository = new SvelteRepository(axiosHttpStub, stubProjectHistoryService());
    const project: Project = createProject({ folder: PROJECT_FOLDER });

    await svelteRepository.addWithStyle(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/clients/svelte/styles');
    expect(payload).toEqual(expectedRestProject);
  });

  it('should update project history when adding', async function () {
    const projectHistoryService = stubProjectHistoryService();
    const svelteRepository = new SvelteRepository(stubAxiosHttp(), projectHistoryService);
    const project: Project = createProject({ folder: PROJECT_FOLDER });

    await svelteRepository.add(project);

    const [projectFolder] = projectHistoryService.get.getCall(0).args;
    expect(projectFolder).toBe(PROJECT_FOLDER);
  });
});
