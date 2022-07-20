package tech.jhipster.lite.generator.init.infrastructure.secondary;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import tech.jhipster.lite.common.domain.Generated;
import tech.jhipster.lite.generator.init.domain.GitRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@Generated(reason = "Can't run unit test on all computer since git may or may not be installed")
class CommandLineGitRepository implements GitRepository {

  @Override
  public void init(JHipsterProjectFolder folder) {
    try {
      Process process = new ProcessBuilder("git", "init").directory(new File(folder.get())).start();

      if (!process.waitFor(10, TimeUnit.SECONDS)) {
        throw new GitInitException("Error during git init, process failed");
      }
    } catch (IOException e) {
      throw new GitInitException("Error during git init: " + e.getMessage(), e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();

      throw new GitInitException("Error during git init: " + e.getMessage(), e);
    }
  }
}
