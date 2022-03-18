import { Project } from '@/springboot/domain/Project';

export interface ProjectService {
  init(project: Project): Promise<void>;
  addMaven(project: Project): Promise<void>;
  addFrontendMavenPlugin(project: Project): Promise<void>;
  addJavaBase(project: Project): Promise<void>;
  addSpringBoot(project: Project): Promise<void>;
  addSpringBootMvcTomcat(project: Project): Promise<void>;
  addPostgres(project: Project): Promise<void>;
  addMySQL(project: Project): Promise<void>;
  addMariaDB(project: Project): Promise<void>;
  addMongoDB(project: Project): Promise<void>;
  addSpringBootFlywayInit(project: Project): Promise<void>;
  addSpringBootFlywayUser(project: Project): Promise<void>;
  addSpringBootLiquibaseInit(project: Project): Promise<void>;
  addSpringBootLiquibaseUser(project: Project): Promise<void>;
}
