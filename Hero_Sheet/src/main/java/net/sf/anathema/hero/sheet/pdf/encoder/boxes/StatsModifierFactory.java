package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;

public interface StatsModifierFactory  {

  HeroStatsModifiers createStatsModifiers(Hero hero);
}