package tech.jhipster.lite.generator.setup.codespace.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
public class CodespaceDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private CodespaceDomainService codespaceDomainService;
}
