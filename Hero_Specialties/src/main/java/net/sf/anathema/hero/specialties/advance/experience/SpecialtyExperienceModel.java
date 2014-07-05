package net.sf.anathema.hero.specialties.advance.experience;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.specialties.model.ISubTraitContainer;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
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
    AbilitiesModel abilitiesModel = AbilitiesModelFetcher.fetch(hero);
    for (Trait ability : abilitiesModel.getAll()) {
      experienceCosts += getExperienceDots(ability) * getCostPerSpecialtyDot(abilitiesModel, ability);
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

  private int getCostPerSpecialtyDot(TraitStateMap stateMap, Trait ability) {
    boolean casteOrFavored = stateMap.getTraitState(ability).isCheapened();
    return experienceData.getSpecialtyCost(casteOrFavored);
  }
}