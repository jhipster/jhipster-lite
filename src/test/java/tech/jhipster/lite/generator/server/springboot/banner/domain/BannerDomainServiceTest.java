package tech.jhipster.lite.generator.server.springboot.banner.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class BannerDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  BannerDomainService bannerDomainService;

  @Test
  void shouldAddBannerJHipsterV7() {
    bannerDomainService.addBannerJHipsterV7(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }

  @Test
  void shouldAddBannerIppon() {
    bannerDomainService.addBannerIppon(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }

  @Test
  void shouldAddBannerJHipsterV7React() {
    bannerDomainService.addBannerJHipsterV7React(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }

  @Test
  void shouldAddBannerJHipsterV7Vue() {
    bannerDomainService.addBannerJHipsterV7Vue(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }

  @Test
  void shouldAddBannerJHipsterV2() {
    bannerDomainService.addBannerJHipsterV2(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }

  @Test
  void shouldAddBannerJHipsterV3() {
    bannerDomainService.addBannerJHipsterV3(tmpProject());
    verify(projectRepository).add(any(ProjectFile.class));
  }
}
