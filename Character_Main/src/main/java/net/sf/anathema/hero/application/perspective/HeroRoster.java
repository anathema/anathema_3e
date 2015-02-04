package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;

public interface HeroRoster extends HeroesGridView {
  void clear();

  HeroIdentifier getIdentifier();
}
