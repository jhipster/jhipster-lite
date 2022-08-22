package tech.jhipster.lite.module.infrastructure.secondary;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.init.domain.InitModuleFactory;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.git.infrastructure.secondary.GitTestUtil;
import tech.jhipster.lite.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModuleEvents;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.resource.JHipsterModulesResourceFixture;
import tech.jhipster.lite.npm.domain.NpmVersion;
import tech.jhipster.lite.npm.domain.NpmVersions;
import tech.jhipster.lite.npm.infrastructure.secondary.FileSystemNpmVersions;
import tech.jhipster.lite.project.infrastructure.primary.JavaProjects;
import tech.jhipster.lite.projectfile.infrastructure.secondary.FileSystemProjectFilesReader;

public final class TestJHipsterModules {

  private static final InitModuleFactory initModules = new InitModuleFactory(mockedNpmVersion());

  private TestJHipsterModules() {}

  private static NpmVersions mockedNpmVersion() {
    NpmVersions npmVersions = mock(NpmVersions.class);

    lenient().when(npmVersions.get(anyString(), any())).thenReturn(new NpmVersion("1.1.1"));

    return npmVersions;
  }

  public static void applyInit(Project project) {
    applyer().module(initModules.buildFullModule(projectProperties(project))).slug("init").apply();
  }

  private static JHipsterModuleProperties projectProperties(Project project) {
    return new JHipsterModuleProperties(project.getFolder(), false, project.getConfig());
  }

  static void apply(JHipsterModule module) {
    applyer().module(module).slug("test-module").apply();
  }

  static TestJHipsterModulesModuleApplyer applyer() {
    return new TestJHipsterModulesApplyer();
  }

  public static class TestJHipsterModulesApplyer
    implements TestJHipsterModulesModuleApplyer, TestJHipsterModulesSlugApplyer, TestJHipsterModulesFinalApplyer {

    private static final JHipsterModulesApplicationService modules = buildApplicationService();

    private JHipsterModule module;
    private JHipsterModuleSlug slug;

    private TestJHipsterModulesApplyer() {}

    private static JHipsterModulesApplicationService buildApplicationService() {
      FileSystemProjectFilesReader filesReader = new FileSystemProjectFilesReader();

      FileSystemJHipsterModulesRepository modulesRepository = new FileSystemJHipsterModulesRepository(
        filesReader,
        new FileSystemNpmVersions(filesReader),
        mock(JavaProjects.class),
        JHipsterModulesResourceFixture.moduleResources()
      );

      return new JHipsterModulesApplicationService(
        mock(JHipsterModuleEvents.class),
        modulesRepository,
        new FileSystemCurrentJavaDependenciesVersionsRepository(filesReader),
        new FileSystemProjectJavaDependenciesRepository(),
        GitTestUtil.gitRepository()
      );
    }

    @Override
    public TestJHipsterModulesSlugApplyer module(JHipsterModule module) {
      Assert.notNull("module", module);

      this.module = module;

      return this;
    }

    @Override
    public TestJHipsterModulesFinalApplyer slug(JHipsterModuleSlug slug) {
      Assert.notNull("slug", slug);

      this.slug = slug;

      return this;
    }

    @Override
    public void apply() {
      modules.apply(new JHipsterModuleToApply(slug, module));
    }
  }

  public interface TestJHipsterModulesModuleApplyer {
    public TestJHipsterModulesSlugApplyer module(JHipsterModule module);
  }

  public interface TestJHipsterModulesSlugApplyer {
    TestJHipsterModulesFinalApplyer slug(JHipsterModuleSlug slug);

    default TestJHipsterModulesFinalApplyer slug(String slug) {
      return slug(new JHipsterModuleSlug(slug));
    }
  }

  public interface TestJHipsterModulesFinalApplyer {
    void apply();
  }
}
