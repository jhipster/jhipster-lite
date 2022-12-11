package tech.jhipster.lite.dsl.generator.java.clazz.domain;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import tech.jhipster.lite.dsl.common.domain.clazz.ClassComment;
import tech.jhipster.lite.dsl.common.domain.clazz.ClassName;
import tech.jhipster.lite.dsl.common.domain.clazz.ClassPackage;
import tech.jhipster.lite.dsl.common.domain.clazz.enums.EnumComment;
import tech.jhipster.lite.dsl.common.domain.clazz.enums.EnumKeyValue;
import tech.jhipster.lite.dsl.common.domain.clazz.enums.EnumName;
import tech.jhipster.lite.dsl.common.domain.clazz.enums.EnumValue;
import tech.jhipster.lite.dsl.generator.java.clazz.domain.enums.EnumElementSimple;
import tech.jhipster.lite.dsl.generator.java.clazz.domain.enums.EnumElementValue;
import tech.jhipster.lite.dsl.parser.domain.clazz.DslEnum;
import tech.jhipster.lite.error.domain.Assert;

public class EnumToGenerate implements FileInfoForGenerate {

  public static EnumToGenerateBuilder enumToGenerateBuilder() {
    return new EnumToGenerateBuilder();
  }

  private Path folder;

  private Path file;
  private EnumName name;
  private ClassPackage packag;

  private List<EnumElementSimple> elementSimple;
  private List<EnumElementValue> elementValue;
  private ClassComment comment;

  public Path getFolder() {
    return folder;
  }

  @Override
  public Path getPathFile() {
    return file;
  }

  public EnumName getName() {
    return name;
  }

  public ClassPackage getPackage() {
    return packag;
  }

  public Optional<ClassComment> getComment() {
    return Optional.ofNullable(comment);
  }

  public List<EnumElementSimple> getElementSimple() {
    return elementSimple;
  }

  public List<EnumElementValue> getElementValue() {
    return elementValue;
  }

  public boolean asImport() {
    return !elementValue.isEmpty();
  }

  @Override
  public String toString() {
    return (
      "EnumToGenerate{" +
      "folder=" +
      folder +
      ", file=" +
      file +
      ", name=" +
      name +
      ", packag=" +
      packag +
      ", elementSimple=" +
      elementSimple +
      ", elementValue=" +
      elementValue +
      ", comment=" +
      comment +
      '}'
    );
  }

  public static final class EnumToGenerateBuilder {

    private Path folder;

    private Path file;
    private EnumName name;
    private ClassPackage packag;

    private final List<EnumElementSimple> elementSimple = new LinkedList<>();
    private final List<EnumElementValue> elementValue = new LinkedList<>();
    private ClassComment comment;

    private EnumToGenerateBuilder() {}

    public EnumToGenerateBuilder fromDslEnum(DslEnum dslEnum) {
      Assert.notNull("dslEnum", dslEnum);
      this.name = dslEnum.getName();
      this.comment = dslEnum.getComment().orElse(null);
      this.packag = dslEnum.getPackage();
      return this;
    }

    public EnumToGenerateBuilder name(EnumName name) {
      Assert.notNull("key", name);
      this.name = name;
      return this;
    }

    public EnumToGenerateBuilder file(Path file) {
      Assert.notNull("file", file);
      this.file = file;
      return this;
    }

    public EnumToGenerateBuilder folder(Path folder) {
      Assert.notNull("folder", folder);
      this.folder = folder;
      return this;
    }

    public EnumToGenerateBuilder addEnumKeyValue(EnumKeyValue enumKeyValue) {
      Assert.notNull("enumKeyValue", enumKeyValue);
      String commentary = null;
      Optional<EnumComment> optComment = enumKeyValue.getComment();
      if (optComment.isPresent()) {
        commentary = optComment.get().comment();
      }
      Optional<EnumValue> optValue = enumKeyValue.getValue();
      if (optValue.isPresent()) {
        this.elementValue.add(new EnumElementValue(enumKeyValue.getKey().key()).value(optValue.get().value()).comment(commentary));
      } else {
        this.elementSimple.add(new EnumElementSimple(enumKeyValue.getKey().key()).comment(commentary));
      }
      return this;
    }

    public EnumToGenerateBuilder comment(ClassComment comment) {
      Assert.notNull("comment", comment);
      this.comment = comment;
      return this;
    }

    public EnumToGenerateBuilder definePackage(ClassPackage definePackage) {
      Assert.notNull("definePackage", definePackage);
      this.packag = definePackage;
      return this;
    }

    public EnumToGenerate build() {
      Assert.notNull("key", this.name);
      Assert.notNull("folder", this.folder);
      Assert.notNull("file", this.file);
      EnumToGenerate enumToGenerate = new EnumToGenerate();
      enumToGenerate.folder = this.folder;
      enumToGenerate.name = this.name;
      enumToGenerate.comment = this.comment;
      enumToGenerate.file = this.file;
      enumToGenerate.elementSimple = this.elementSimple;
      enumToGenerate.elementValue = this.elementValue;
      enumToGenerate.packag = this.packag;
      return enumToGenerate;
    }
  }
}
