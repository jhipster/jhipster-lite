package tech.jhipster.lite.generator.history.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GeneratorHistoryDomainServiceTest {

  @Mock
  GeneratorHistoryRepository generatorHistoryRepository;

  @InjectMocks
  GeneratorHistoryDomainService historyDomainService;

  @Test
  void shouldGetHistoryData() {
    // Given
    Project project = tmpProject();

    GeneratorHistoryData expectedHistoryData = new GeneratorHistoryData();
    when(generatorHistoryRepository.getHistoryData(project)).thenReturn(expectedHistoryData);

    // When
    GeneratorHistoryData generatorHistoryData = historyDomainService.getHistoryData(project);

    // Then
    assertThat(generatorHistoryData).isEqualTo(expectedHistoryData);
  }

  @Test
  void shouldAddHistoryValue() {
    // Given
    Project project = tmpProject();

    // When
    GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue("tomcat");
    historyDomainService.addHistoryValue(project, generatorHistoryValue);

    // Then
    verify(generatorHistoryRepository).addHistoryValue(project, generatorHistoryValue);
  }
}
