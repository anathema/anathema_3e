package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class TraitPrerequisite extends ReflectionEqualsObject {

  public static TraitPrerequisite Create(Identifier type, int value) {
    return new TraitPrerequisite(new RequiredTraitType(type.getId()), value);
  }

  public static TraitPrerequisite Create(String type, int value) {
    return new TraitPrerequisite(new RequiredTraitType(type), value);
  }

  public final RequiredTraitType type;
  public final int minimalValue;

  public TraitPrerequisite(RequiredTraitType type, int minimalValue) {
    this.type = type;
    this.minimalValue = minimalValue;
  }
}
