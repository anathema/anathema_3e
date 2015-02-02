package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSystemModel;

import java.util.ArrayList;
import java.util.List;

public class HeroStackPresenter {
  private final List<HeroIdentifier> knownCharacters = new ArrayList<>();
  private final ItemSystemModel model;
  private final HeroStackBridge bridge;

  public HeroStackPresenter(HeroStackBridge bridge, ItemSystemModel model) {
    this.bridge = bridge;
    this.model = model;
  }

  public void showCharacter(HeroIdentifier identifier) {
    if (!knownCharacters.contains(identifier)) {
      addViewForCharacter(identifier);
      knownCharacters.add(identifier);
    }
    bridge.showHeroView(identifier);
    model.setCurrentCharacter(identifier);
  }

  private void addViewForCharacter(HeroIdentifier identifier) {
    Item item = model.loadItem(identifier);
    bridge.addViewForHero(identifier, (HeroItemData) item.getItemData());
  }
}
