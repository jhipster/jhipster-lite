package tech.jhipster.lite.generator.init.infrastructure.secondary;

import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import tech.jhipster.lite.generator.init.domain.GitRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

class JGitGitRepository implements GitRepository {

  @Override
  public void init(JHipsterProjectFolder folder) {
    try {
      Git.init().setDirectory(new File(folder.get())).call();
    } catch (IllegalStateException | GitAPIException | JGitInternalException e) {
      throw new GitInitException("Error during git init: " + e.getMessage(), e);
    }
  }
}
