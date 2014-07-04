package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.framework.perspective.CharacterStackBridge;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.library.fx.NodeHolder;

public class CharacterStackFxBridge implements CharacterStackBridge {

  private final StackView stackView;
  private final CharacterViewFactory viewFactory;

  public CharacterStackFxBridge(CharacterViewFactory viewFactory, StackView stackView) {
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
