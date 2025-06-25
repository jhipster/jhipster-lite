package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static tech.jhipster.lite.module.domain.resource.JHipsterModulesResourceFixture.defaultModuleResourceBuilder;
import static tech.jhipster.lite.module.domain.resource.JHipsterModulesResourceFixture.emptyHiddenModules;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import tech.jhipster.lite.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModuleEvents;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.module.domain.JHipsterPresetRepository;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.resource.JHipsterModulesResources;
import tech.jhipster.lite.module.infrastructure.secondary.file.MustacheTemplateRenderer;
import tech.jhipster.lite.module.infrastructure.secondary.git.GitTestUtil;
import tech.jhipster.lite.module.infrastructure.secondary.javabuild.FileSystemProjectJavaBuildToolRepository;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.FileSystemJavaBuildCommandsHandler;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.JavaDependenciesFixture;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.JavaDependenciesReader;
import tech.jhipster.lite.module.infrastructure.secondary.nodejs.NodePackagesVersionsReader;
import tech.jhipster.lite.module.infrastructure.secondary.nodejs.NpmVersionsFixture;
import tech.jhipster.lite.project.infrastructure.primary.JavaProjects;

public final class TestJHipsterModules {

  private static final Set<NodePackagesVersionsReader> customNodePackagesVersionsReader = Collections.newSetFromMap(
    new ConcurrentHashMap<>()
  );
  private static final Set<JavaDependenciesReader> customJavaDependenciesReaders = Collections.newSetFromMap(new ConcurrentHashMap<>());

  private TestJHipsterModules() {}

  public static void register(NodePackagesVersionsReader nodePackagesVersionsReader) {
    assertThat(nodePackagesVersionsReader).as("Npm versions reader to register can't be null").isNotNull();

    customNodePackagesVersionsReader.add(nodePackagesVersionsReader);
  }

  public static void register(JavaDependenciesReader javaDependenciesReader) {
    assertThat(javaDependenciesReader).as("Java dependencies reader to register can't be null").isNotNull();

    customJavaDependenciesReaders.add(javaDependenciesReader);
  }

  public static void unregisterReaders() {
    customNodePackagesVersionsReader.clear();
    customJavaDependenciesReaders.clear();
  }

  static void apply(JHipsterModule module) {
    applyer(module).apply();
  }

  private static TestJHipsterModulesFinalApplyer applyer(JHipsterModule module) {
    return new TestJHipsterModulesApplyer(module);
  }

  private static final class TestJHipsterModulesApplyer implements TestJHipsterModulesFinalApplyer {

    private final JHipsterModule module;
    private final JHipsterModuleSlug slug;
    private final JHipsterModulesApplicationService modules;

    private TestJHipsterModulesApplyer(JHipsterModule module) {
      this.module = module;
      this.slug = new JHipsterModuleSlug("test-module");
      this.modules = buildApplicationService(module);
    }

    private static JHipsterModulesApplicationService buildApplicationService(JHipsterModule module) {
      ProjectFiles filesReader = new FileSystemProjectFiles();
      MustacheTemplateRenderer templateRenderer = new MustacheTemplateRenderer();
      FileSystemReplacer fileReplacer = new FileSystemReplacer(templateRenderer);
      FileSystemJHipsterModuleFiles files = new FileSystemJHipsterModuleFiles(filesReader, templateRenderer);

      FileSystemJHipsterModulesRepository modulesRepository = new FileSystemJHipsterModulesRepository(
        mock(JavaProjects.class),
        new JHipsterModulesResources(
          List.of(defaultModuleResourceBuilder().slug("test-module").factory(properties -> module).build()),
          emptyHiddenModules()
        ),
        files,
        fileReplacer,
        new FileSystemGitIgnoreHandler(fileReplacer),
        new FileSystemJavaBuildCommandsHandler(new FileSystemProjectJavaBuildToolRepository(), files, fileReplacer),
        new FileSystemPackageJsonHandler(NpmVersionsFixture.npmVersions(filesReader, customNodePackagesVersionsReader), templateRenderer),
        new FileSystemStartupCommandsReadmeCommandsHandler(fileReplacer)
      );

      return new JHipsterModulesApplicationService(
        mock(JHipsterModuleEvents.class),
        modulesRepository,
        JavaDependenciesFixture.javaVersionsRepository(filesReader, customJavaDependenciesReaders),
        JavaDependenciesFixture.projectVersionsRepository(),
        new FileSystemProjectJavaBuildToolRepository(),
        GitTestUtil.gitRepository(),
        new FileSystemGeneratedProjectRepository(),
        mock(JHipsterPresetRepository.class)
      );
    }

    @Override
    public void apply() {
      modules.apply(new JHipsterModuleToApply(slug, module.properties()));
    }
  }

  public interface TestJHipsterModulesFinalApplyer {
    void apply();
  }
}
