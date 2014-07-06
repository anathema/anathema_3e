package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharmConfiguration;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

import java.util.HashSet;
import java.util.Set;

public class CharmExperienceModel extends AbstractIntegerValueModel {

  private final CharmExperienceCostCalculator calculator;
  private final Hero hero;

  public CharmExperienceModel(CharmExperienceCostCalculator calculator, Hero hero) {
    super("Experience", "Charms");
    this.calculator = calculator;
    this.hero = hero;
  }

  @Override
  public Integer getValue() {
    return getCharmCosts();
  }

  private int getCharmCosts() {
    int experienceCosts = 0;
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    Set<Charm> charmsCalculated = new HashSet<>();
    for (Charm charm : charmsModel.getLearningModel().getCharmsLearnedWithExperience()) {
      int charmCosts = calculateCharmCost(charmsModel, charm);
      if (charmsModel.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
      charmsCalculated.add(charm);
    }
    return experienceCosts;
  }

  private int calculateCharmCost(CharmsModel charms, Charm charm) {
    CharmSpecialsModel specialCharm = charms.getCharmSpecialsModel(charm);
    int charmCost = calculator.getCharmCosts(hero, charm);
    LearningModel learnModel = charms.getLearningModel();
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration) {
        IUpgradableCharmConfiguration upgradableSpecial = (IUpgradableCharmConfiguration) specialCharm;
        return (learnModel.isLearnedWithExperience(
          charm) ? charmCost : 0) + upgradableSpecial.getUpgradeXPCost();
      }
      if (!(specialCharm instanceof SubEffectCharmSpecials)) {
        return specialCharmCost;
      }
      SubEffectCharmSpecials subEffectCharmConfiguration = (SubEffectCharmSpecials) specialCharm;
      int count = Math.max(0,
        (subEffectCharmConfiguration.getExperienceLearnedSubEffectCount() - (subEffectCharmConfiguration.getCreationLearnedSubEffectCount() == 0 ? 1 : 0)));
      int subEffectCost = (int) Math.ceil(count * subEffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subEffectCost + specialCharmCost;
    }
    return learnModel.isLearnedWithExperience(charm) ? charmCost : 0;
  }

}