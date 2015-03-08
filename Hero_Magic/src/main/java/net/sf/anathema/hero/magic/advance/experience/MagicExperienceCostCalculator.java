package net.sf.anathema.hero.magic.advance.experience;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.costs.GenericCostAnalyzer;
import net.sf.anathema.magic.data.Magic;

public class MagicExperienceCostCalculator {

  private final MagicExperienceData costs;

  public MagicExperienceCostCalculator(MagicExperienceData costs) {
    this.costs = costs;
  }

  public int getMagicCosts(Hero hero, Magic magic) {
    return costs.getMagicCosts(magic, new GenericCostAnalyzer(hero));
  }
}