import { Project } from '@/generator/domain/Project';
import { ProjectService } from '@/generator/domain/ProjectService';
import { AxiosHttp } from '@/http/AxiosHttp';

export default class ProjectRepository implements ProjectService {
  constructor(private axiosHttp: AxiosHttp) {}

  init(project: Project) {
    return this.axiosHttp.post('api/projects/init', project);
  }
}
