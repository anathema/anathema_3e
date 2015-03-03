package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.library.model.AbstractEntryOptionSupplier;
import net.sf.anathema.library.model.OptionalEntryCategory;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AbilityOptionSupplier extends AbstractEntryOptionSupplier<SpecialtyType> {

  @Override
  public List<SpecialtyType> getAllOptions() {
    return Arrays.asList(AbilityType.values()).stream().map(ability -> new SpecialtyTypeImpl(ability)).collect(toList());
  }

  @Override
  public List<SpecialtyType> getAllOptionsForCategory(
      OptionalEntryCategory category) {
    return getAllOptions();
  }
  
}
