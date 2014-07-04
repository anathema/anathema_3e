package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Collection;

public interface EquipmentHeroEvaluator extends ItemAttunementEvaluator {

  IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type);

  Collection<Specialty> getSpecialties(TraitType trait);
}