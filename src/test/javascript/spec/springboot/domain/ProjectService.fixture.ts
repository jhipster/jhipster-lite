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
  addSpringBootBannerJhipsterV2: SinonStub;
  addSpringBootBannerJhipsterV3: SinonStub;
  addSpringBootBannerJhipsterV7: SinonStub;
  addSpringBootBannerJhipsterV7React: SinonStub;
  addSpringBootBannerJhipsterV7Vue: SinonStub;
}

export const stubProjectService = (): ProjectServiceFixture => ({
  init: sinon.stub(),
  addMaven: sinon.stub(),
  addFrontendMavenPlugin: sinon.stub(),
  addJavaBase: sinon.stub(),
  addSpringBoot: sinon.stub(),
  addSpringBootMvcTomcat: sinon.stub(),
  addSpringBootBannerIppon: sinon.stub(),
  addSpringBootBannerJhipsterV2: sinon.stub(),
  addSpringBootBannerJhipsterV3: sinon.stub(),
  addSpringBootBannerJhipsterV7: sinon.stub(),
  addSpringBootBannerJhipsterV7React: sinon.stub(),
  addSpringBootBannerJhipsterV7Vue: sinon.stub(),
});
