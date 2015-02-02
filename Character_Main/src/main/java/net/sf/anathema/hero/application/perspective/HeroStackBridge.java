package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;

public interface HeroStackBridge {

  void addViewForHero(HeroIdentifier identifier, HeroItemData heroItemData);

  void showHeroView(HeroIdentifier identifier);
}
