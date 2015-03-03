package net.sf.anathema.hero.specialties.advance.creation;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.points.model.BonusPointCalculator;

import java.util.List;

public class SpecialtiesBonusPointCalculator implements BonusPointCalculator {

  private SpecialtyCalculator specialtyCalculator;
  private Hero hero;
  private int specialtyBonusPointCosts;
  private int specialtyDotSum;

  public SpecialtiesBonusPointCalculator(Hero hero, SpecialtyCreationData creationData) {
    this.hero = hero;
    this.specialtyCalculator = new SpecialtyCalculator(creationData);
  }

  @Override
  public void recalculate() {
    clear();
    List<Specialty> specialties = SpecialtiesModelFetcher.fetch(hero).getEntries();
    specialtyDotSum = specialtyCalculator.getFreebiePointsSpent(specialties);
    specialtyBonusPointCosts = specialtyCalculator.getSpecialtyCosts(specialties);
  }

  private void clear() {
    specialtyDotSum = 0;
    specialtyBonusPointCosts = 0;
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
