package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.hero.framework.display.SectionView;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.platform.environment.Environment;

public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero, Environment environment);

  boolean canWorkForHero(Hero hero);
}
