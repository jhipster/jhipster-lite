package tech.jhipster.light.generator.project.infrastructure.secondary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    log.info("Create Git repository: {}", dir);
    return Git.init().setDirectory(new File(dir)).call();
  }

  public static void addAndCommit(String dir, String message) throws GitAPIException, IOException {
    add(dir);
    commit(dir, message);
  }

  public static void add(String dir) throws GitAPIException, IOException {
    log.debug("Adding all files to repository {}", dir);
    Git git = getGit(dir);
    git.add().addFilepattern(".").call();
  }

  public static void commit(String dir, String message) throws GitAPIException, IOException {
    log.debug("Commiting all files to repository {}", dir);
    Git git = getGit(dir);
    git.commit().setCommitter(USERNAME, EMAIL).setMessage(message).call();
  }

  public static void apply(String dir, String patch) throws GitAPIException, IOException {
    log.debug("Apply patch to repository {}: {}", dir, patch);
    Git git = getGit(dir);
    FileInputStream fileInputStream = new FileInputStream(patch);
    git.apply().setPatch(fileInputStream).call();
  }

  public static Git getGit(String dir) throws IOException {
    return Git.open(new File(dir));
  }
}
