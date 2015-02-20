package net.sf.anathema.hero.equipment.model;

import java.util.Collection;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.ArtifactAttuneType;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;
import net.sf.anathema.hero.equipment.IEquipmentStatsOption;
import net.sf.anathema.hero.equipment.SpecialtiesCollectionImpl;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;

public class EquipmentHeroEvaluatorImpl implements EquipmentHeroEvaluator {

  private Hero hero;

  public EquipmentHeroEvaluatorImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public Collection<Specialty> getSpecialties(TraitType trait) {
    return new SpecialtiesCollectionImpl(hero).getSpecialties(trait);
  }

  @Override
  public IEquipmentStatsOption getCharacterSpecialtyOption(String name, String type) {
    TraitType trait = AbilityType.valueOf(type);
    for (Specialty specialty : getSpecialties(trait)) {
      if (specialty.getName().equals(name)) {
        return new EquipmentSpecialtyOption(specialty, trait);
      }
    }
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
    return new ArtifactAttuneType[] { ArtifactAttuneType.Unattuned, ArtifactAttuneType.Attuned };
  }
}