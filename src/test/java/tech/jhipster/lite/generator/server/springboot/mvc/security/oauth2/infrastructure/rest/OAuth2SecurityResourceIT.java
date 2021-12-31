package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application.OAuth2SecurityAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.project.infrastructure.secondary.GitUtils;
import tech.jhipster.lite.generator.server.javatool.base.application.JavaBaseApplicationService;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application.OAuth2SecurityApplicationService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.primary.dto.OAuth2ClientDTO;
import tech.jhipster.lite.generator.server.springboot.mvc.web.application.SpringBootMvcApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class OAuth2SecurityResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  JavaBaseApplicationService javaBaseApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  SpringBootMvcApplicationService springBootMvcApplicationService;

  @Autowired
  OAuth2SecurityApplicationService oAuth2SecurityApplicationService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    OAuth2ClientDTO oAuth2ClientDTO = new OAuth2ClientDTO();
    oAuth2ClientDTO.setProject(projectDTO);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/security/oauth2")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(oAuth2ClientDTO))
      )
      .andExpect(status().isOk());

    assertSecurityDependencies(project);
    assertOAuth2ClientDependencies(project);
    assertOAuth2ClientProperties(project, oAuth2ClientDTO.getProvider(), oAuth2ClientDTO.getIssuerUri());
  }

  @Test
  void shouldAddDefault() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/security/oauth2/default")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    // TODO
  }

  @Test
  void shouldAddJwt() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/security/oauth2/jwt")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    // TODO
  }

  @Test
  void shouldAddOpaqueToken() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/security/oauth2/opaque-token")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    // TODO
  }

  @Test
  void shouldAddAccount() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/security/oauth2/account")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    // TODO
  }
}
