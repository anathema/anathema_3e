package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public class AttributeTraitType implements TraitType {

  private final String name;

  public AttributeTraitType(String name) {
    this.name = name;
  }

  @Override
  public String getId() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    AttributeTraitType that = (AttributeTraitType) o;
    return this.name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}