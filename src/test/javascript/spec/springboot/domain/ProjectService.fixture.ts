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
  addSpringBootBannerIppon: SinonStub;
  addSpringBootBannerJHipsterV2: SinonStub;
  addSpringBootBannerJHipsterV3: SinonStub;
  addSpringBootBannerJHipsterV7: SinonStub;
  addSpringBootBannerJHipsterV7React: SinonStub;
  addSpringBootBannerJHipsterV7Vue: SinonStub;
  addPostgres: SinonStub;
  addMySQL: SinonStub;
  addMariaDB: SinonStub;
  addMongoDB: SinonStub;
  addSpringBootFlywayInit: SinonStub;
  addSpringBootFlywayUser: SinonStub;
  addSpringBootLiquibaseInit: SinonStub;
  addSpringBootLiquibaseUser: SinonStub;
  addJWT: SinonStub;
  addBasicAuthJWT: SinonStub;
  addOauth2: SinonStub;
  addSpringdocJWT: SinonStub;
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
  addPostgres: sinon.stub(),
  addMySQL: sinon.stub(),
  addMariaDB: sinon.stub(),
  addMongoDB: sinon.stub(),
  addSpringBootFlywayInit: sinon.stub(),
  addSpringBootFlywayUser: sinon.stub(),
  addSpringBootLiquibaseInit: sinon.stub(),
  addSpringBootLiquibaseUser: sinon.stub(),
  addJWT: sinon.stub(),
  addBasicAuthJWT: sinon.stub(),
  addOauth2: sinon.stub(),
  addSpringdocJWT: sinon.stub(),
});
