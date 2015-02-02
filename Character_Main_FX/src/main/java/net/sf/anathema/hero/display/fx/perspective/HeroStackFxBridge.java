package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.perspective.HeroStackBridge;
import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.fx.NodeHolder;

public class HeroStackFxBridge implements HeroStackBridge {

  private final StackView stackView;
  private final HeroViewFactory viewFactory;

  public HeroStackFxBridge(HeroViewFactory viewFactory, StackView stackView) {
    this.viewFactory = viewFactory;
    this.stackView = stackView;
  }

  @Override
  public void addViewForCharacter(CharacterIdentifier identifier, HeroItemData heroItemData) {
    NodeHolder itemView = viewFactory.createView(heroItemData);
    stackView.addView(identifier, itemView);
  }

  @Override
  public void showCharacterView(CharacterIdentifier identifier) {
    stackView.showView(identifier);
  }
}
