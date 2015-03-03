package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Collection;

public interface EquipmentHeroEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  Collection<Specialty> getSpecialties(TraitType trait);
}