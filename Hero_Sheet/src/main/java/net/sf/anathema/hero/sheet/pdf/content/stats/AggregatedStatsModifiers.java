package net.sf.anathema.hero.sheet.pdf.content.stats;

import java.util.ArrayList;
import java.util.List;

public class AggregatedStatsModifiers implements HeroStatsModifiers {
  private final List<HeroStatsModifiers> modifiers = new ArrayList<>();

  @Override
  public int getMobilityPenalty() {
    int mod = 0;
    for (HeroStatsModifiers modifier : modifiers) {
      mod += modifier.getMobilityPenalty();
    }
    return mod;
  }

  public void add(HeroStatsModifiers statsModifiers) {
    modifiers.add(statsModifiers);
  }
}
