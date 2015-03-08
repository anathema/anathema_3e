package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.CommonMagicAttributes;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecials;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

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
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == null) {
      return 0;
    }
    int experienceCosts = 0;
    for (Charm charm : charmsModel.getLearningModel().getCharmsLearnedWithExperience()) {
      if (!charm.hasAttribute(CommonMagicAttributes.NO_PURCHASE)) {
        int charmCosts = calculateCharmCost(charmsModel, charm);
        if (charmsModel.isAlienCharm(charm)) {
          charmCosts *= 2;
        }
        experienceCosts += charmCosts;
      }
    }
    return experienceCosts;
  }

  private int calculateCharmCost(CharmsModel charms, Charm charm) {
    CharmSpecialLearningModel specialCharm = charms.getCharmSpecialLearningModel(charm);
    int charmCost = calculator.getCharmCosts(hero, charm);
    LearningModel learnModel = charms.getLearningModel();
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
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