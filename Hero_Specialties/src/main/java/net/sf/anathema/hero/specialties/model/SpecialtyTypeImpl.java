package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.property.AbstractOptionalPropertyOption;

public class SpecialtyTypeImpl extends AbstractOptionalPropertyOption implements SpecialtyType {

  private final TraitType type;
  private final TraitTypeInternationalizer i18;
  
  public SpecialtyTypeImpl(TraitType type, TraitTypeInternationalizer i18) {
    this.type = type;
    this.i18 = i18;
  }

  @Override
  public TraitType getTraitType() {
    return type;
  }
  
  @Override
  public String toString() {
    return i18 != null ? i18.getScreenName(type) : type.toString();
  }

}
