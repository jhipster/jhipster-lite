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
    await this.axiosHttp.post('api/servers/spring-boot/mvc/web/tomcat', restProject);
  }

  async addSpringBootBannerIppon(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/ippon', restProject);
  }

  async addSpringBootBannerJHipsterV2(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/jhipster-v2', restProject);
  }

  async addSpringBootBannerJHipsterV3(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/jhipster-v3', restProject);
  }

  async addSpringBootBannerJHipsterV7(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/jhipster-v7', restProject);
  }

  async addSpringBootBannerJHipsterV7React(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/jhipster-v7-react', restProject);
  }

  async addSpringBootBannerJHipsterV7Vue(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('api/servers/spring-boot/banner/jhipster-v7-vue', restProject);
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
    await this.axiosHttp.post('/api/servers/spring-boot/databases/migration/flyway/init', restProject);
  }

  async addSpringBootFlywayUser(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/migration/flyway/user', restProject);
  }

  async addSpringBootLiquibaseInit(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/migration/liquibase/init', restProject);
  }

  async addSpringBootLiquibaseUser(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/databases/migration/liquibase/user', restProject);
  }

  async addJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/mvc/security/jwt', restProject);
  }

  async addBasicAuthJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/mvc/security/jwt/basic-auth', restProject);
  }

  async addOauth2(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/mvc/security/oauth2', restProject);
  }

  async addSpringdocJWT(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt', restProject);
  }

  async addSpringBootAsync(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/async', restProject);
  }

  async addSpringBootDevtoolsDependencies(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/devtools', restProject);
  }

  async addSpringBootDockerfile(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/docker/dockerfile', restProject);
  }

  async addSpringBootDockerJib(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/docker/jib', restProject);
  }

  async addSpringCloudConfigClient(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/spring-cloud/config-client', restProject);
  }

  async addSpringCloudConsul(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/spring-cloud/consul', restProject);
  }

  async addSpringCloudEureka(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/spring-cloud/eureka-client', restProject);
  }

  async addEhcacheWithJavaConf(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/ehcache/java-configuration', restProject);
  }

  async addEhcacheWithXML(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/ehcache/xml-configuration', restProject);
  }

  async addSimpleCache(project: Project): Promise<void> {
    const restProject: RestProject = toRestProject(project);
    await this.axiosHttp.post('/api/servers/spring-boot/cache/simple', restProject);
  }
}
