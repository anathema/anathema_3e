package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.model.Hero;

public class CharmExperienceCostCalculator {

  private final MagicExperienceData costs;

  public CharmExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getCharmCosts(Hero hero, Charm charm) {
    return costs.getMagicCosts(charm, new CostAnalyzerImpl(hero));
  }
}