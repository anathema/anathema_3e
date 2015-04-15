package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public class AbilityTraitType implements TraitType {

  private final String name;

  public AbilityTraitType(String name) {
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
    AbilityTraitType that = (AbilityTraitType) o;
    return this.name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}