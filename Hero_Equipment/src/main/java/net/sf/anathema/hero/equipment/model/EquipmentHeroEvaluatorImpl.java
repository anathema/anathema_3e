package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;
import net.sf.anathema.hero.equipment.IEquipmentStatsOption;
import net.sf.anathema.hero.equipment.SpecialtiesCollectionImpl;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.DefaultTraitType;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Collection;

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
    TraitType trait = new DefaultTraitType(type);
    for (Specialty specialty : getSpecialties(trait)) {
      if (specialty.getDescription().equals(name)) {
        return new EquipmentSpecialtyOption(specialty, trait);
      }
    }
    return null;
  }
}