package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.shared.error.domain.Assert;

public record AddTasksTestInstruction(String instruction) implements JavaBuildCommand {
  public AddTasksTestInstruction {
    Assert.notNull("instruction", instruction);
  }

  public String get() {
    return instruction;
  }
}
