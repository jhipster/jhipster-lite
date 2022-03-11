import { ProjectService } from '@/springboot/domain/ProjectService';
import sinon, { SinonStub } from 'sinon';

export interface ProjectServiceFixture extends ProjectService {
  init: SinonStub;
  addMaven: SinonStub;
  addFrontendMavenPlugin: SinonStub;
  addJavaBase: SinonStub;
  addSpringBoot: SinonStub;
  addSpringBootMvcTomcat: SinonStub;
}

export const stubProjectService = (): ProjectServiceFixture => ({
  init: sinon.stub(),
  addMaven: sinon.stub(),
  addFrontendMavenPlugin: sinon.stub(),
  addJavaBase: sinon.stub(),
  addSpringBoot: sinon.stub(),
  addSpringBootMvcTomcat: sinon.stub(),
});
