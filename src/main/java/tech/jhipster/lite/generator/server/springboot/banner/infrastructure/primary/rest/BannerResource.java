package tech.jhipster.lite.generator.server.springboot.banner.infrastructure.primary.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.banner.application.BannerApplicationService;
import tech.jhipster.lite.technical.infrastructure.primary.annotation.GeneratorStep;

@RestController
@RequestMapping("/api/servers/spring-boot/banners")
@Tag(name = "Spring Boot")
class BannerResource {

  private final BannerApplicationService bannerApplicationService;

  public BannerResource(BannerApplicationService bannerApplicationService) {
    this.bannerApplicationService = bannerApplicationService;
  }

  @Operation(summary = "Add banner JHipster v7 for Angular")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/jhipster-v7")
  @GeneratorStep(id = GeneratorAction.BANNER_JHIPSTER_V7)
  void addBannerJHipsterV7(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerJHipsterV7(projectDTO.toModuleProperties());
  }

  @Operation(summary = "Add banner JHipster v7 for React")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/jhipster-v7-react")
  @GeneratorStep(id = GeneratorAction.BANNER_JHIPSTER_V7_REACT)
  void addBannerJHipsterV7React(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerJHipsterV7React(projectDTO.toModuleProperties());
  }

  @Operation(summary = "Add banner JHipster v7 for Vue")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/jhipster-v7-vue")
  @GeneratorStep(id = GeneratorAction.BANNER_JHIPSTER_V7_VUE)
  void addBannerJHipsterV7Vue(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerJHipsterV7Vue(projectDTO.toModuleProperties());
  }

  @Operation(summary = "Add banner JHipster v2")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/jhipster-v2")
  @GeneratorStep(id = GeneratorAction.BANNER_JHIPSTER_V2)
  void addBannerJHipsterV2(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerJHipsterV2(projectDTO.toModuleProperties());
  }

  @Operation(summary = "Add banner JHipster v3")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/jhipster-v3")
  @GeneratorStep(id = GeneratorAction.BANNER_JHIPSTER_V3)
  void addBannerJHipsterV3(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerJHipsterV3(projectDTO.toModuleProperties());
  }

  @Operation(summary = "Add banner for Ippon applications")
  @ApiResponse(responseCode = "500", description = "An error occurred while adding banner")
  @PostMapping("/ippon")
  @GeneratorStep(id = GeneratorAction.BANNER_IPPON)
  void addBannerIppon(@RequestBody ProjectDTO projectDTO) {
    bannerApplicationService.addBannerIppon(projectDTO.toModuleProperties());
  }
}
