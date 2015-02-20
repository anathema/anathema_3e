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

  @Override
  public int getDDVPoolMod() {
    int mod = 0;
    for (HeroStatsModifiers modifier : modifiers) {
      mod += modifier.getDDVPoolMod();
    }
    return mod;
  }

  @Override
  public int getJoinBattleMod() {
    int mod = 0;
    for (HeroStatsModifiers modifier : modifiers) {
      mod += modifier.getJoinBattleMod();
    }
    return mod;
  }

  public void add(HeroStatsModifiers statsModifiers) {
    modifiers.add(statsModifiers);
  }
}
