package tech.jhipster.forge.common.secondary;

import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitUtils {

  private static final Logger log = LoggerFactory.getLogger(GitUtils.class);

  public static final String USERNAME = "JHipster Bot";
  public static final String EMAIL = "jhipster-bot@jhipster.tech";

  private GitUtils() {}

  public static Git init(String dir) throws GitAPIException {
    log.info("Create Git repository for {}", dir);
    return Git.init().setDirectory(new File(dir)).call();
  }

  public static void add(Git git, String dir) throws GitAPIException {
    log.debug("Adding all files to repository {}", dir);
    git.add().addFilepattern(".").call();
  }

  public static void commit(Git git, String dir, String message) throws GitAPIException {
    log.debug("Commiting all files to repository {}", dir);
    git.commit().setCommitter(USERNAME, EMAIL).setMessage(message).call();
  }
}
