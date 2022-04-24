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
    expect(uri).toBe('api/servers/spring-boot/web-servers/tomcat');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add SpringBoot Webflux with Netty', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootWebfluxNetty(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/reactive-servers/netty');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add SpringBoot Actuator', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootActuator(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('api/servers/spring-boot/technical-tools/actuator');
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
    expect(uri).toBe('api/servers/spring-boot/banners/ippon');
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
    expect(uri).toBe('api/servers/spring-boot/banners/jhipster-v2');
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
    expect(uri).toBe('api/servers/spring-boot/banners/jhipster-v3');
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
    expect(uri).toBe('api/servers/spring-boot/banners/jhipster-v7');
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
    expect(uri).toBe('api/servers/spring-boot/banners/jhipster-v7-react');
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
    expect(uri).toBe('api/servers/spring-boot/banners/jhipster-v7-vue');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add PostgreSQL', () => {
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

  it('should add MySQL', () => {
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

  it('should add Flyway', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootFlywayInit(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/database-migration-tools/flyway');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add User with Flyway', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootFlywayUser(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/database-migration-tools/flyway/user');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Liquibase', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootLiquibaseInit(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/database-migration-tools/liquibase');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add User with Liquibase', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootLiquibaseUser(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/database-migration-tools/liquibase/user');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Spring Security JWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/security-systems/jwt');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Basic Auth for Spring Security JWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addBasicAuthJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/security-systems/jwt/basic-auth');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add a Spring Security OAuth 2.0 / OIDC', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addOauth2(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/security-systems/oauth2');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Springdoc with Security JWT', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringdocJWT(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/api-documentations/springdoc/init-with-security-jwt');
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

  it('should add Developer Tools dependencies', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDevtoolsDependencies(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/technical-tools/devtools');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Dockerfile', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDockerfile(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/containers/docker/dockerfile');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add docker image building with Jib', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringBootDockerJib(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/containers/docker/jib');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Spring Cloud Config Client', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudConfigClient(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/distributed-systems/spring-cloud/config-client');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Spring Cloud Consul config and discovery', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudConsul(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/distributed-systems/spring-cloud/consul');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });

  it('should add Spring Cloud Eureka Client', () => {
    const axiosHttpStub = stubAxiosHttp();
    axiosHttpStub.post.resolves();
    const springBootRepository = new SpringBootRepository(axiosHttpStub);
    const project: Project = createProject({ folder: 'folder/path' });

    springBootRepository.addSpringCloudEureka(project);

    const expectedRestProject: RestProject = toRestProject(project);
    const [uri, payload] = axiosHttpStub.post.getCall(0).args;
    expect(uri).toBe('/api/servers/spring-boot/distributed-systems/spring-cloud/eureka-client');
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
    expect(uri).toBe('/api/servers/spring-boot/caches/ehcache/java-configuration');
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
    expect(uri).toBe('/api/servers/spring-boot/caches/ehcache/xml-configuration');
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
    expect(uri).toBe('/api/servers/spring-boot/caches/simple');
    expect(payload).toEqual<RestProject>(expectedRestProject);
  });
});
