package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.projectfolder.domain.ProjectFolder;

@RestController
@Tag(name = "Modules")
@RequestMapping("/api")
class ModulesResource {

  private final JHipsterModulesApplicationService modules;
  private final ProjectFolder projectFolder;

  private final RestJHipsterModules modulesList;
  private final RestJHipsterLandscape modulesLandscape;
  private final Map<String, JHipsterModuleResource> modulesMap;

  public ModulesResource(JHipsterModulesApplicationService modules, ProjectFolder projectFolder) {
    this.modules = modules;
    this.projectFolder = projectFolder;

    modulesMap = modules.resources().stream().collect(Collectors.toMap(module -> module.slug().get(), Function.identity()));
    modulesList = RestJHipsterModules.from(modules.resources());
    modulesLandscape = RestJHipsterLandscape.from(modules.landscape());
  }

  @GetMapping("/modules")
  @Operation(summary = "List available modules")
  public ResponseEntity<RestJHipsterModules> listModules() {
    return ResponseEntity.ok(modulesList);
  }

  @GetMapping("modules-landscape")
  @Operation(summary = "Get a view of the current modules landscape")
  public ResponseEntity<RestJHipsterLandscape> modulesLandscape() {
    return ResponseEntity.ok(modulesLandscape);
  }

  @PostMapping("apply-patches")
  @Operation(summary = "Apply multiple modules patches")
  public void applyPatches(@RequestBody @Validated RestJHipsterModulesToApply modulesToApply) {
    modules.apply(modulesToApply.toDomain(projectFolder));
  }

  @PostMapping("modules/{slug}/apply-patch")
  public void applyPatch(@RequestBody @Validated RestJHipsterModuleProperties restProperties, @PathVariable("slug") String slug) {
    if (!modulesMap.containsKey(slug)) {
      throw InvalidModuleQueryException.unknownModule(slug);
    }
    JHipsterModuleProperties properties = restProperties.toDomain(projectFolder);
    JHipsterModuleResource module = modulesMap.get(slug);
    modules.apply(new JHipsterModuleToApply(module.slug(), module.factory().create(properties)));
  }

  @GetMapping("modules/{slug}")
  public RestJHipsterModulePropertiesDefinition propertiesDefinition(@PathVariable("slug") String slug) {
    if (!modulesMap.containsKey(slug)) {
      throw InvalidModuleQueryException.unknownModule(slug);
    }
    JHipsterModuleResource module = modulesMap.get(slug);
    return RestJHipsterModulePropertiesDefinition.from(module.propertiesDefinition());
  }
}
