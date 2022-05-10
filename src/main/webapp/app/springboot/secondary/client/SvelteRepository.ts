import { AxiosHttp } from '@/http/AxiosHttp';
import { Project } from '../../domain/Project';
import { RestProject, toRestProject } from '../RestProject';
import { ProjectHistoryService } from '@/common/domain/ProjectHistoryService';
import { SvelteService } from '@/springboot/domain/client/SvelteService';

export default class SvelteRepository implements SvelteService {
  constructor(private axiosHttp: AxiosHttp, private projectHistoryService: ProjectHistoryService) {}

  private async postAndGetHistory(url: string, restProject: RestProject): Promise<void> {
    await this.axiosHttp.post(url, restProject);
    await this.projectHistoryService.get(restProject.folder);
  }

  async add(project: Project): Promise<void> {
    await this.postAndGetHistory('/api/clients/svelte', toRestProject(project));
  }

  async addWithStyle(project: Project) {
    await this.postAndGetHistory('/api/clients/svelte/styles', toRestProject(project));
  }
}
