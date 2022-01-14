package tech.jhipster.lite.generator.packagemanager.npm.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import tech.jhipster.lite.UnitTest;

@UnitTest
@ExtendWith(MockitoExtension.class)
class NpmLocalRepositoryTest {

  @InjectMocks
  NpmLocalRepository repository;

  @Mock
  Process process;

  @Test
  void shouldWait() throws Exception {
    ReflectionTestUtils.setField(repository, "timeout", 120);
    when(process.waitFor(anyLong(), any(TimeUnit.class))).thenThrow(InterruptedException.class);

    assertThatCode(() -> repository.wait(process)).doesNotThrowAnyException();
  }
}
