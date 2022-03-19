import { Project } from '@/springboot/domain/Project';
import SpringBootRepository from '@/springboot/secondary/SpringBootRepository';
import { stubAxiosHttp } from '../../http/AxiosHttpStub';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';
import { createProject } from '../domain/Project.fixture';

describe('SpringBootRepository', () => {
  it('should add SpringBoot', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBoot(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add SpringBoot MVC with Tomcat', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootMvcTomcat(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/mvc/web/tomcat');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Ippon Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerIppon(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/ippon');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JHipster v2 Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerJHipsterV2(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/jhipster-v2');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JHipster v3 Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerJHipsterV3(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/jhipster-v3');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JHipster v7 Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerJHipsterV7(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/jhipster-v7');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JHipster v7 React Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerJHipsterV7React(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/jhipster-v7-react');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JHipster v7 Vue Banner', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootBannerJHipsterV7Vue(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/banner/jhipster-v7-vue');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add Postgres', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addPostgres(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/postgresql');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add Mysql', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addMySQL(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/mysql');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add MariaDB', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addMariaDB(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/mariadb');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add MongoDB', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addMongoDB(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/mongodb');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should migrate flyway init', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootFlywayInit(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/migration/flyway/init');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should migrate flyway user', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootFlywayUser(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/migration/flyway/user');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should migrate liquibase init', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootLiquibaseInit(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/migration/liquibase/init');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should migrate liquibase user', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootLiquibaseUser(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/databases/migration/liquibase/user');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add JWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/mvc/security/jwt');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add Basic Auth JWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addBasicAuthJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/mvc/security/jwt/basic-auth');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add Oauth2', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addOauth2(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/mvc/security/oauth2');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add SpringdocJWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringdocJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add asynchronous execution', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootAsync(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/async');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add devtools dependencies', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDevtoolsDependencies(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/devtools');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add dockerfile', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDockerfile(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/docker/dockerfile');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add docker jib', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDockerJib(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/docker/jib');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add client config on spring cloud', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudConfigClient(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/spring-cloud/config-client');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add spring cloud consul config', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudConsul(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/spring-cloud/consul');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add eureka on spring cloud', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudEureka(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/spring-cloud/eureka-client');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Ehcache with Java configuration', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addEhcacheWithJavaConf(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/cache/ehcache/java-configuration');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add Ehcache with XML', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addEhcacheWithXML(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/cache/ehcache/xml-configuration');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
  it('should add simple cache', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSimpleCache(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/cache/simple');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
});
