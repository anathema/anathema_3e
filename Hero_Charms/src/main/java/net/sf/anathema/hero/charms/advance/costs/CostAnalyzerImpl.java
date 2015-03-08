package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.costs.CostAnalyzer;
import net.sf.anathema.magic.data.Magic;

public class CostAnalyzerImpl implements CostAnalyzer {

  private final Hero hero;

  public CostAnalyzerImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public final boolean isMagicFavored(Magic magic) {
  	CharmsModel charms = CharmsModelFetcher.fetch(hero);
    return charms == null ? false : charms.isMagicCheapened(magic);
  }
}