package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application.GatewayApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/spring-boot/distributed-systems/spring-cloud/gateway")
@Tag(name = "Spring Boot - Spring Cloud")
class GatewayResource {

  private final GatewayApplicationService gatewayApplicationService;

  public GatewayResource(GatewayApplicationService gatewayApplicationService) {
    this.gatewayApplicationService = gatewayApplicationService;
  }

  @Operation(summary = "Add Spring Cloud Gateway")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding Spring Cloud Gateway")
  @PostMapping
  @GeneratorStep(id = GeneratorAction.EUREKA_CLIENT)
  public void init(@RequestBody ProjectDTO projectDTO) {
    gatewayApplicationService.init(ProjectDTO.toProject(projectDTO));
  }
}
