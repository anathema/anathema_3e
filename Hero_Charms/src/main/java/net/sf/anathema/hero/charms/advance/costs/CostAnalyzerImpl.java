package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.magic.data.Magic;

public class CostAnalyzerImpl implements CostAnalyzer {

  private final Hero hero;

  public CostAnalyzerImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public final boolean isMagicFavored(Magic magic) {
    return CharmsModelFetcher.fetch(hero).isFavoredMagic(magic);
  }

  @Override
  public MartialArtsLevel getMartialArtsLevel(Magic magic) {
    return MartialArtsUtilities.getLevel(magic);
  }
}