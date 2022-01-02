package tech.jhipster.lite.generator.project.infrastructure.secondary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.secondary.executables.Npm;

@Repository
public class CommandLocalRepository implements CommandRepository {

  private final Logger log = LoggerFactory.getLogger(CommandLocalRepository.class);

  private final Executor taskExecutor;

  @Value("${application.cmd.timeout:120}")
  private Integer timeout;

  public CommandLocalRepository(Executor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @Override
  public void npmInstall(Project project) {
    try {
      this.runProcess(project, Npm.getExecutableCommand(), "install");
    } catch (IOException e) {
      e.printStackTrace();
      throw new GeneratorException("Error when running \"npm install\"");
    }
  }

  @Override
  public void npmPrettierFormat(Project project) {
    try {
      this.runProcess(project, Npm.getExecutableCommand(), "run", "prettier:format");
    } catch (IOException e) {
      throw new GeneratorException("Error when running \"npm run prettier:format\"");
    }
  }

  private void runProcess(Project project, String... command) throws IOException {
    log.info("Running command: \"{}\" in directory: \"{}\"", command, project.getFolder());

    File workingDir = new File(project.getFolder());
    String line;
    ProcessBuilder processBuilder = new ProcessBuilder()
      .directory(workingDir)
      .command(command)
      .redirectError(ProcessBuilder.Redirect.DISCARD);
    Process process = processBuilder.start();
    taskExecutor.execute(() -> wait(process));

    try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      while ((line = input.readLine()) != null) {
        log.info(line);
      }
    }
  }

  public void wait(Process process) {
    try {
      process.waitFor(timeout, TimeUnit.SECONDS);
      process.destroyForcibly();
    } catch (InterruptedException e) {
      log.error("Unable to execute process successfully.", e);
      Thread.currentThread().interrupt();
    }
  }
}
