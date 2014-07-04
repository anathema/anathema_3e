package net.sf.anathema.hero.specialties.sheet.content;

import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;

public class NamedSpecialtyReference implements ValuedTraitReference {

  private final String name;
  private final TraitType type;
  private final int value;

  public NamedSpecialtyReference(Specialty trait, TraitType type) {
    this.type = type;
    this.name = trait.getName();
    this.value = trait.getCurrentValue();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public TraitType getTraitType() {
    return type;
  }

  @Override
  public int getValue() {
    return value;
  }
}
