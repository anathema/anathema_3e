package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.hero.charms.model.favored.IsCharmCheapened;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.experience.MagicExperienceData;
import net.sf.anathema.magic.data.Charm;

public class CharmExperienceCostCalculator {

  private final MagicExperienceData costs;

  public CharmExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getCharmCosts(Hero hero, Charm charm) {
    return costs.getMagicCosts(charm, new IsCharmCheapened(hero));
  }
}