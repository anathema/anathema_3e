package net.sf.anathema.hero.charms.model.additional;

import net.sf.anathema.hero.individual.splat.HeroType;

public abstract class ExcellencyAdditionalRules implements AdditionalCharmRules {

  private final HeroType heroType;

  protected ExcellencyAdditionalRules(HeroType heroType) {
    this.heroType = heroType;
  }

  protected String getStringForExcellency(String trait) {
    return heroType.getId() + ".Excellent" + heroType.getId() + trait;
  }
}