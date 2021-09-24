package tech.jhipster.forge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import tech.jhipster.forge.common.utils.JhforgeUtils;

@SpringBootApplication
public class JhforgeApp {

  private static final Logger log = LoggerFactory.getLogger(JhforgeApp.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(JhforgeApp.class);
    Environment env = app.run(args).getEnvironment();
    logApplicationStartup(env);
  }

  private static void logApplicationStartup(Environment env) {
    String protocol = JhforgeUtils.getProtocol(env.getProperty("server.ssl.key-store"));
    String serverPort = env.getProperty("server.port");
    String contextPath = JhforgeUtils.getContextPath(env.getProperty("server.servlet.context-path"));
    String hostAddress = JhforgeUtils.getHostAddress();
    log.info(
      "\n----------------------------------------------------------\n\t" +
      "Application '{}' is running! Access URLs:\n\t" +
      "Local: \t\t{}://localhost:{}{}\n\t" +
      "External: \t{}://{}:{}{}\n\t" +
      "Profile(s): \t{}\n----------------------------------------------------------",
      env.getProperty("spring.application.name"),
      protocol,
      serverPort,
      contextPath,
      protocol,
      hostAddress,
      serverPort,
      contextPath,
      env.getActiveProfiles()
    );
  }
}
