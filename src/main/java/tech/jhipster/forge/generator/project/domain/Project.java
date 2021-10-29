package tech.jhipster.forge.generator.project.domain;

import java.util.Optional;
import tech.jhipster.forge.error.domain.Assert;

public class Project {

  private final String folder;
  private final Optional<Language> language;
  private final Optional<BuildToolType> buildTool;
  private final Optional<Server> server;
  private final Optional<Client> client;

  private Project(ProjectBuilder builder) {
    Assert.notBlank("folder", builder.folder);

    this.folder = builder.folder;
    this.language = Optional.ofNullable(builder.language);
    this.buildTool = Optional.ofNullable(builder.buildTool);
    this.server = Optional.ofNullable(builder.server);
    this.client = Optional.ofNullable(builder.client);
  }

  public static ProjectBuilder builder() {
    return new ProjectBuilder();
  }

  public String getFolder() {
    return folder;
  }

  public Optional<Language> getLanguage() {
    return language;
  }

  public Optional<BuildToolType> getBuildTool() {
    return buildTool;
  }

  public Optional<Server> getServer() {
    return server;
  }

  public Optional<Client> getClient() {
    return client;
  }

  public static class ProjectBuilder {

    private String folder;
    private Language language;
    private BuildToolType buildTool;
    private Server server;
    private Client client;

    public Project build() {
      return new Project(this);
    }

    public ProjectBuilder folder(String folder) {
      this.folder = folder;
      return this;
    }

    public ProjectBuilder language(Language language) {
      this.language = language;
      return this;
    }

    public ProjectBuilder buildTool(BuildToolType buildTool) {
      this.buildTool = buildTool;
      return this;
    }

    public ProjectBuilder server(Server server) {
      this.server = server;
      return this;
    }

    public ProjectBuilder client(Client client) {
      this.client = client;
      return this;
    }
  }
}
