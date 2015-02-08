package net.sf.anathema.hero.display.fx.perspective.content;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.perspective.HeroStackBridge;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.individual.view.HeroView;

public class HeroStackFxBridge implements HeroStackBridge {

  private final StackView stackView;
  private final HeroViewFactory viewFactory;

  public HeroStackFxBridge(HeroViewFactory viewFactory, StackView stackView) {
    this.viewFactory = viewFactory;
    this.stackView = stackView;
  }

  @Override
  public HeroView addViewForHero(HeroIdentifier identifier, HeroItemData hero) {
    TopBarHeroView heroView = viewFactory.createView(hero);
    stackView.addView(identifier, heroView);
    return heroView;
  }

  @Override
  public void showHeroView(HeroIdentifier identifier) {
    stackView.showView(identifier);
  }
}
