import { ProjectService } from '@/springboot/domain/ProjectService';
import sinon, { SinonStub } from 'sinon';

export interface ProjectServiceFixture extends ProjectService {
  init: SinonStub;
  addMaven: SinonStub;
  addFrontendMavenPlugin: SinonStub;
  addJavaBase: SinonStub;
  addSpringBoot: SinonStub;
  addSpringBootMvcTomcat: SinonStub;
  addSpringBootBannerIppon: SinonStub;
  addSpringBootBannerJHipsterV2: SinonStub;
  addSpringBootBannerJHipsterV3: SinonStub;
  addSpringBootBannerJHipsterV7: SinonStub;
  addSpringBootBannerJHipsterV7React: SinonStub;
  addSpringBootBannerJHipsterV7Vue: SinonStub;
}

export const stubProjectService = (): ProjectServiceFixture => ({
  init: sinon.stub(),
  addMaven: sinon.stub(),
  addFrontendMavenPlugin: sinon.stub(),
  addJavaBase: sinon.stub(),
  addSpringBoot: sinon.stub(),
  addSpringBootMvcTomcat: sinon.stub(),
  addSpringBootBannerIppon: sinon.stub(),
  addSpringBootBannerJHipsterV2: sinon.stub(),
  addSpringBootBannerJHipsterV3: sinon.stub(),
  addSpringBootBannerJHipsterV7: sinon.stub(),
  addSpringBootBannerJHipsterV7React: sinon.stub(),
  addSpringBootBannerJHipsterV7Vue: sinon.stub(),
});
