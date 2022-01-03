package tech.jhipster.lite.generator.project.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.error.domain.UnauthorizedValueException;

public class Project {

  private final String folder;
  private final Map<String, Object> config;
  private final Optional<LanguageType> language;
  private final Optional<BuildToolType> buildTool;
  private final Optional<ServerFrameworkType> server;
  private final Optional<ClientFrameworkType> client;
  private final Optional<DatabaseType> database;
  private final Optional<DatabaseMigrationType> databaseMigration;
  private final Optional<CacheType> cache;
  private final Optional<SecurityType> security;

  private Project(ProjectBuilder builder) {
    Assert.notBlank("folder", builder.folder);
    Assert.notNull("config", builder.config);

    this.folder = builder.folder;
    this.config = builder.config;
    this.language = Optional.ofNullable(builder.language);
    this.buildTool = Optional.ofNullable(builder.buildTool);
    this.server = Optional.ofNullable(builder.server);
    this.client = Optional.ofNullable(builder.client);
    this.database = Optional.ofNullable(builder.database);
    this.databaseMigration = Optional.ofNullable(builder.databaseMigration);
    this.cache = Optional.ofNullable(builder.cache);
    this.security = Optional.ofNullable(builder.security);

    validateProject();
  }

  public static ProjectBuilder builder() {
    return new ProjectBuilder();
  }

  public String getFolder() {
    return folder;
  }

  public Map<String, Object> getConfig() {
    return config;
  }

  public Optional<LanguageType> getLanguage() {
    return language;
  }

  public Optional<BuildToolType> getBuildTool() {
    return buildTool;
  }

  public Optional<ServerFrameworkType> getServer() {
    return server;
  }

  public Optional<ClientFrameworkType> getClient() {
    return client;
  }

  public Optional<DatabaseType> getDatabase() {
    return database;
  }

  public Optional<DatabaseMigrationType> getDatabaseMigration() {
    return databaseMigration;
  }

  public Optional<CacheType> getCache() {
    return cache;
  }

  public Optional<SecurityType> getSecurity() {
    return security;
  }

  public Optional<Object> getConfig(String key) {
    return Optional.ofNullable(config.get(key));
  }

  public void addConfig(String key, Object value) {
    config.putIfAbsent(key, value);
    validateConfig();
  }

  public void addDefaultConfig(String key) {
    DefaultConfig.get(key).ifPresent(value -> addConfig(key, value));
  }

  public Optional<String> getBaseName() {
    return getStringConfig(BASE_NAME);
  }

  public Optional<String> getPackageName() {
    return getStringConfig(PACKAGE_NAME);
  }

  public Optional<String> getPackageNamePath() {
    return getPackageName().map(packageName -> packageName.replace(".", "/"));
  }

  public Optional<String> getStringConfig(String key) {
    Object value = config.get(key);
    if (value == null) {
      return Optional.empty();
    } else if (value instanceof String stringValue) {
      return Optional.of(stringValue);
    }
    throw new UnauthorizedValueException("The " + key + " is not valid");
  }

  public Optional<Integer> getIntegerConfig(String key) {
    Object value = config.get(key);
    if (value == null) {
      return Optional.empty();
    } else if (value instanceof Integer integerValue) {
      return Optional.of(integerValue);
    }
    throw new UnauthorizedValueException("The " + key + " is not valid");
  }

  private void validateConfig() {
    getBaseName().ifPresent(CheckConfig::validBaseName);
    getPackageName().ifPresent(CheckConfig::validPackageName);
  }

  public boolean isMavenProject() {
    return FileUtils.exists(getPath(getFolder(), POM_XML));
  }

  public boolean isGradleProject() {
    return FileUtils.exists(getPath(getFolder(), "build.gradle"));
  }

  public void checkBuildTool() {
    if (!isMavenProject() && !isGradleProject()) {
      throw new GeneratorException("No build tool");
    }
  }

  public void validateProject() {
    checkFolder();
  }

  public void checkFolder() {
    if ("/".equals(folder)) {
      throw new UnauthorizedValueException("The generation folder shall not be set to '/'");
    }
  }

  public static class ProjectBuilder {

    private String folder;
    private Map<String, Object> config = new HashMap<>();
    private LanguageType language;
    private BuildToolType buildTool;
    private ServerFrameworkType server;
    private ClientFrameworkType client;
    private DatabaseType database;
    private DatabaseMigrationType databaseMigration;
    private CacheType cache;
    private SecurityType security;

    public Project build() {
      return new Project(this);
    }

    public ProjectBuilder folder(String folder) {
      this.folder = folder;
      return this;
    }

    public ProjectBuilder config(Map<String, Object> config) {
      if (config != null) {
        this.config = config;
      }
      return this;
    }

    public ProjectBuilder language(LanguageType language) {
      this.language = language;
      return this;
    }

    public ProjectBuilder buildTool(BuildToolType buildTool) {
      this.buildTool = buildTool;
      return this;
    }

    public ProjectBuilder server(ServerFrameworkType server) {
      this.server = server;
      return this;
    }

    public ProjectBuilder client(ClientFrameworkType client) {
      this.client = client;
      return this;
    }

    public ProjectBuilder database(DatabaseType database) {
      this.database = database;
      return this;
    }

    public ProjectBuilder databaseMigration(DatabaseMigrationType databaseMigration) {
      this.databaseMigration = databaseMigration;
      return this;
    }

    public ProjectBuilder cache(CacheType cache) {
      this.cache = cache;
      return this;
    }

    public ProjectBuilder security(SecurityType security) {
      this.security = security;
      return this;
    }
  }
}
