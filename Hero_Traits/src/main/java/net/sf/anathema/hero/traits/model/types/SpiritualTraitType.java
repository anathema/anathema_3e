package net.sf.anathema.hero.traits.model.types;

import net.sf.anathema.hero.traits.model.TraitType;

public class SpiritualTraitType implements TraitType {

  private final String name;

  public SpiritualTraitType(String name) {
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
    SpiritualTraitType that = (SpiritualTraitType) o;
    return this.name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}