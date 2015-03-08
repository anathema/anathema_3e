package net.sf.anathema.hero.magic.advance.costs;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.MagicPointsModel;
import net.sf.anathema.hero.magic.advance.MagicPointsModelFetcher;
import net.sf.anathema.magic.data.Magic;

public class CostAnalyzerImpl implements CostAnalyzer {

  private final Hero hero;

  public CostAnalyzerImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public final boolean isMagicFavored(Magic magic) {
    MagicPointsModel points = MagicPointsModelFetcher.fetch(hero);
    return points!= null && points.isMagicCheapened(magic);
  }
}