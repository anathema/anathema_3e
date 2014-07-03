package net.sf.anathema.hero.specialties.advance.experience;

import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.specialties.ISubTraitContainer;
import net.sf.anathema.hero.specialties.SpecialtiesModel;
import net.sf.anathema.hero.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class SpecialtyExperienceModel extends AbstractIntegerValueModel {

  private Hero hero;
  private SpecialtyExperienceData experienceData;

  public SpecialtyExperienceModel(Hero hero, SpecialtyExperienceData experienceData) {
    super("Experience", "Specialties");
    this.hero = hero;
    this.experienceData = experienceData;
  }

  @Override
  public Integer getValue() {
    return getSpecialtyCosts();
  }

  private int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (Trait ability : AbilityModelFetcher.fetch(hero).getAll()) {
      experienceCosts += getExperienceDots(ability) * getCostPerSpecialtyDot(ability);
    }
    return experienceCosts;
  }

  private int getExperienceDots(Trait ability) {
    ISubTraitContainer specialtiesContainer = getSpecialtyContainer(ability);
    return specialtiesContainer.getExperienceDotTotal();
  }

  private ISubTraitContainer getSpecialtyContainer(Trait ability) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    return specialtyConfiguration.getSpecialtiesContainer(ability.getType());
  }

  private int getCostPerSpecialtyDot(Trait ability) {
    boolean casteOrFavored = ability.getFavorization().isCasteOrFavored();
    return experienceData.getSpecialtyCost(casteOrFavored);
  }
}