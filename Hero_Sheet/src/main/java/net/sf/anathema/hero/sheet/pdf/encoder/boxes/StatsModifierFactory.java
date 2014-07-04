package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.framework.library.HeroStatsModifiers;
import net.sf.anathema.hero.individual.model.Hero;

public interface StatsModifierFactory  {

  HeroStatsModifiers createStatsModifiers(Hero hero);
}