package tech.jhipster.lite.generator.module.infrastructure.primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import tech.jhipster.lite.generator.module.application.JHipsterModulesApplicationService;

@Component
class JHipsterModulesHandlerMapping extends AbstractUrlHandlerMapping {

  private final Map<String, JHipsterProjectQueryController> modules;

  public JHipsterModulesHandlerMapping(
    ObjectMapper json,
    Collection<JHipsterModuleResource> moduleResources,
    JHipsterModulesApplicationService modules
  ) {
    this.modules = buildModules(json, moduleResources, modules);
  }

  private Map<String, JHipsterProjectQueryController> buildModules(
    ObjectMapper json,
    Collection<JHipsterModuleResource> moduleResources,
    JHipsterModulesApplicationService modules
  ) {
    assertModules(moduleResources);

    return moduleResources
      .stream()
      .collect(
        Collectors.toUnmodifiableMap(JHipsterModuleResource::legacyUrl, module -> new JHipsterProjectQueryController(json, module, modules))
      );
  }

  private void assertModules(Collection<JHipsterModuleResource> modules) {
    assertUniqSlugs(modules);
  }

  private void assertUniqSlugs(Collection<JHipsterModuleResource> modules) {
    if (duplicatedSlug(modules)) {
      throw new DuplicatedSlugException();
    }
  }

  private boolean duplicatedSlug(Collection<JHipsterModuleResource> modules) {
    return modules.stream().map(JHipsterModuleResource::slug).collect(Collectors.toSet()).size() != modules.size();
  }

  @Override
  protected void initApplicationContext() throws BeansException {
    setOrder(0);
    super.initApplicationContext();
    registerHandlers();
  }

  private void registerHandlers() {
    modules.forEach(this::registerHandler);
  }
}
