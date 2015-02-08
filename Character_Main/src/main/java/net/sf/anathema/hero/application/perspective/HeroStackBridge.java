package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.individual.view.HeroView;

public interface HeroStackBridge {

  HeroView addViewForHero(HeroIdentifier identifier, HeroItemData heroItemData);

  void showHeroView(HeroIdentifier identifier);
}