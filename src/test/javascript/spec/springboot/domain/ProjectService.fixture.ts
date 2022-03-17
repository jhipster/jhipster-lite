import { ProjectService } from '@/springboot/domain/ProjectService';
import sinon, { SinonStub } from 'sinon';
import { Project } from '../../../../../main/webapp/app/springboot/domain/Project';

export interface ProjectServiceFixture extends ProjectService {
  init: SinonStub;
  addMaven: SinonStub;
  addFrontendMavenPlugin: SinonStub;
  addJavaBase: SinonStub;
  addSpringBoot: SinonStub;
  addSpringBootMvcTomcat: SinonStub;
  addSpringBootAsync: SinonStub;
  addSpringBootDevtoolsDependencies: SinonStub;
  addSpringBootDockerfile: SinonStub;
  addSpringBootDockerJib: SinonStub;
}

export const stubProjectService = (): ProjectServiceFixture => ({
  init: sinon.stub(),
  addMaven: sinon.stub(),
  addFrontendMavenPlugin: sinon.stub(),
  addJavaBase: sinon.stub(),
  addSpringBoot: sinon.stub(),
  addSpringBootMvcTomcat: sinon.stub(),
  addSpringBootAsync: sinon.stub(),
  addSpringBootDevtoolsDependencies: sinon.stub(),
  addSpringBootDockerfile: sinon.stub(),
  addSpringBootDockerJib: sinon.stub(),
});
