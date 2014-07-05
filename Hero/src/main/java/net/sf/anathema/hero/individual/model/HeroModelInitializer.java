package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.individual.view.SectionView;

public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero);

  boolean canWorkForHero(Hero hero);
}
