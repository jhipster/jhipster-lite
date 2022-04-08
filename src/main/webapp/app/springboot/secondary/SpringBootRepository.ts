import { Project } from '@/springboot/domain/Project';
import { SpringBootService } from '@/springboot/domain/SpringBootService';
import { AxiosHttp } from '@/http/AxiosHttp';
import { RestProject, toRestProject } from '@/springboot/secondary/RestProject';

export default class SpringBootRepository implements SpringBootService {
  constructor(private axiosHttp: AxiosHttp) {}

  async addSpringBoot(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot', restProject);
  }

  async addSpringBootMvcTomcat(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/web-servers/tomcat', restProject);
  }

  async addSpringBootBannerIppon(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/ippon', restProject);
  }

  async addSpringBootBannerJHipsterV2(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/jhipster-v2', restProject);
  }

  async addSpringBootBannerJHipsterV3(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/jhipster-v3', restProject);
  }

  async addSpringBootBannerJHipsterV7(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/jhipster-v7', restProject);
  }

  async addSpringBootBannerJHipsterV7React(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/jhipster-v7-react', restProject);
  }

  async addSpringBootBannerJHipsterV7Vue(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banners/jhipster-v7-vue', restProject);
  }

  async addPostgres(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/postgresql', restProject);
  }

  async addMySQL(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/mysql', restProject);
  }

  async addMariaDB(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/mariadb', restProject);
  }

  async addMongoDB(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/mongodb', restProject);
  }
  async addSpringBootFlywayInit(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/database-migration-tools/flyway', restProject);
  }

  async addSpringBootFlywayUser(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/database-migration-tools/flyway/user', restProject);
  }

  async addSpringBootLiquibaseInit(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/database-migration-tools/liquibase', restProject);
  }

  async addSpringBootLiquibaseUser(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/database-migration-tools/liquibase/user', restProject);
  }

  async addJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/security-systems/jwt', restProject);
  }

  async addBasicAuthJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/security-systems/jwt/basic-auth', restProject);
  }

  async addOauth2(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/security-systems/oauth2', restProject);
  }

  async addSpringdocJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/api-documentations/springdoc/init-with-security-jwt', restProject);
  }

  async addSpringBootAsync(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/async', restProject);
  }

  async addSpringBootDevtoolsDependencies(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/technical-tools/devtools', restProject);
  }

  async addSpringBootDockerfile(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/containers/docker/dockerfile', restProject);
  }

  async addSpringBootDockerJib(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/containers/docker/jib', restProject);
  }

  async addSpringCloudConfigClient(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/distributed-systems/spring-cloud/config-client', restProject);
  }

  async addSpringCloudConsul(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/distributed-systems/spring-cloud/consul', restProject);
  }

  async addSpringCloudEureka(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/distributed-systems/spring-cloud/eureka-client', restProject);
  }

  async addEhcacheWithJavaConf(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/caches/ehcache/java-configuration', restProject);
  }

  async addEhcacheWithXML(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/caches/ehcache/xml-configuration', restProject);
  }

  async addSimpleCache(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/caches/simple', restProject);
  }
}
