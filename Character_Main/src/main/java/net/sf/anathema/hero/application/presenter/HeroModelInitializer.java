package net.sf.anathema.hero.application.presenter;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.view.SectionView;

public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero);

  boolean canWorkForHero(Hero hero);
}
