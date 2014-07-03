package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharmConfiguration;
import net.sf.anathema.hero.model.Hero;
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
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
    Set<Charm> charmsCalculated = new HashSet<>();
    for (Charm charm : charmConfiguration.getLearnedCharms(true)) {
      int charmCosts = calculateCharmCost(charmConfiguration, charm, charmsCalculated);
      if (charmConfiguration.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
      charmsCalculated.add(charm);
    }
    return experienceCosts;
  }

  private int calculateCharmCost(CharmsModel charms, Charm charm, Set<Charm> charmsCalculated) {
    CharmSpecialsModel specialCharm = charms.getCharmSpecialsModel(charm);
    int charmCost = calculator.getCharmCosts(hero, charm);
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration) {
        return (charms.getGroup(charm).isLearned(charm, true) ? charmCost : 0) +
               ((IUpgradableCharmConfiguration) specialCharm).getUpgradeXPCost();
      }
      if (!(specialCharm instanceof SubEffectCharmSpecials)) {
        return specialCharmCost;
      }
      SubEffectCharmSpecials subEffectCharmConfiguration = (SubEffectCharmSpecials) specialCharm;
      int count = Math.max(0, (subEffectCharmConfiguration.getExperienceLearnedSubEffectCount() -
                               (subEffectCharmConfiguration.getCreationLearnedSubEffectCount() == 0 ? 1 : 0)));
      int subEffectCost = (int) Math.ceil(count * subEffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subEffectCost + specialCharmCost;
    }
    return charms.getGroup(charm).isLearned(charm, true) ? charmCost : 0;
  }

}