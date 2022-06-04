package tech.jhipster.lite.generator.module.infrastructure.primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleFactory;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

class JHipsterProjectQueryController implements Controller {

  private final ObjectMapper json;
  private final JHipsterModulesApplicationService modules;
  private final JHipsterModuleSlug slug;
  private final JHipsterModuleFactory factory;

  public JHipsterProjectQueryController(ObjectMapper json, JHipsterModuleResource module, JHipsterModulesApplicationService modules) {
    Assert.notNull("json", json);
    Assert.notNull("module", module);

    this.json = json;
    this.modules = modules;
    slug = module.slug();
    factory = module.factory();
  }

  @Override
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    if (!HttpMethod.POST.matches(request.getMethod())) {
      throw InvalidProjectQueryException.unsupportedMethod();
    }

    try {
      ProjectDTO dto = json.readValue(StreamUtils.copyToByteArray(request.getInputStream()), ProjectDTO.class);
      JHipsterModuleProperties properties = dto.toModuleProperties();

      modules.apply(new JHipsterModuleToApply(ProjectDTO.toProject(dto), slug, factory.create(properties)));
    } catch (IOException e) {
      throw InvalidProjectQueryException.unreadablePayload(e);
    }

    return null;
  }
}
