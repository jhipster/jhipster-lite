package tech.jhipster.lite.module.domain.standalonedocker;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleDockerComposeFile {

  private final DockerComposeFiles dockerComposeFiles;

  private JHipsterModuleDockerComposeFile(JHipsterModuleDockerComposeFileBuilder builder) {
    dockerComposeFiles = new DockerComposeFiles(builder.dockerComposeFiles);
  }

  public DockerComposeFiles dockerComposeFiles() {
    return dockerComposeFiles;
  }

  @Override
  public String toString() {
    return dockerComposeFiles.toString();
  }

  public static JHipsterModuleDockerComposeFileBuilder builder(JHipsterModule.JHipsterModuleBuilder parentModuleBuilder) {
    return new JHipsterModuleDockerComposeFileBuilder(parentModuleBuilder);
  }

  public static final class JHipsterModuleDockerComposeFileBuilder {

    private final Collection<DockerComposeFile> dockerComposeFiles = new ArrayList<>();
    private final JHipsterModule.JHipsterModuleBuilder module;

    private JHipsterModuleDockerComposeFileBuilder(JHipsterModule.JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleDockerComposeFileBuilder append(DockerComposeFile dockerComposeFile) {
      Assert.notNull("dockerComposeFile", dockerComposeFile);

      dockerComposeFiles.add(dockerComposeFile);

      return this;
    }

    public JHipsterModule.JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleDockerComposeFile build() {
      return new JHipsterModuleDockerComposeFile(this);
    }
  }
}
