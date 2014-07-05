package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.ISubTraitContainer;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.advance.TraitCalculationUtilities;
import net.sf.anathema.hero.traits.model.GroupedTraitsModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.points.model.BonusPointCalculator;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesBonusPointCalculator implements BonusPointCalculator {

  private SpecialtyCalculator specialtyCalculator;
  private Hero hero;
  private TraitMap traitMap;
  private SpecialtyCreationData creationData;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;

  public SpecialtiesBonusPointCalculator(Hero hero, GroupedTraitsModel traitMap, SpecialtyCreationData creationData) {
    this.hero = hero;
    this.traitMap = traitMap;
    this.creationData = creationData;
    this.specialtyCalculator = new SpecialtyCalculator(traitMap, creationData.getCreationDots());
  }

  @Override
  public void recalculate() {
    clear();
    IGenericSpecialty[] specialties = createGenericSpecialties();
    specialtyDotSum = specialtyCalculator.getSpecialtyPointsSpent(specialties);
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
  }

  private void clear() {
    specialtyDotSum = 0;
    specialtyBonusPointCosts = 0;
  }

  private IGenericSpecialty[] createGenericSpecialties() {
    List<IGenericSpecialty> specialties = new ArrayList<>();
    for (Trait ability : traitMap.getAll()) {
      SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
      ISubTraitContainer specialtiesContainer = specialtiesModel.getSpecialtiesContainer(ability.getType());
      for (Specialty specialty : specialtiesContainer.getSubTraits()) {
        int calculationValue = TraitCalculationUtilities.getCreationCalculationValue(specialty, creationData);
        for (int index = 0; index < calculationValue; index++) {
          specialties.add(new GenericSpecialty(ability));
        }
      }
    }
    return specialties.toArray(new IGenericSpecialty[specialties.size()]);
  }

  @Override
  public int getBonusPointCost() {
    return specialtyBonusPointCosts;
  }

  public int getFreePointsSpent() {
    return specialtyDotSum;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}
