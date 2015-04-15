package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.AbstractEntryOptionSupplier;
import net.sf.anathema.library.model.OptionalEntryCategory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class AbilityOptionSupplier extends AbstractEntryOptionSupplier<SpecialtyType> {

  private final TraitTypeInternationalizer i18;
  private final List<TraitType> abilityTypes;

  public AbilityOptionSupplier(TraitTypeInternationalizer i18, List<TraitType> abilityTypes) {
    this.i18 = i18;
    this.abilityTypes = abilityTypes;
  }
  
  @Override
  public List<SpecialtyType> getAllOptions() {
    return abilityTypes.stream().map(ability -> new SpecialtyTypeImpl(ability, i18)).collect(toList());
  }

  @Override
  public List<SpecialtyType> getAllOptionsForCategory(OptionalEntryCategory category) {
    return getAllOptions();
  }
}