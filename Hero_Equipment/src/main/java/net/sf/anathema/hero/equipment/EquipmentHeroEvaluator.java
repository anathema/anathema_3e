package net.sf.anathema.hero.equipment;

import java.util.Collection;

import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;

public interface EquipmentHeroEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  Collection<Specialty> getSpecialties(TraitType trait);
}