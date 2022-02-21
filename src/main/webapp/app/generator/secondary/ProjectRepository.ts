import { Project } from '@/main/webapp/app/generator/domain/Project';
import { ProjectService } from '@/main/webapp/app/generator/domain/ProjectService';
import { AxiosHttp } from '@/main/webapp/app/http/AxiosHttp';

export default class ProjectRepository implements ProjectService {
  constructor(private axiosHttp: AxiosHttp) {}

  init(project: Project) {
    return this.axiosHttp.post('url/to/init', project);
  }
}
