package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSystemModel;
import net.sf.anathema.hero.application.presenter.HeroPresenter;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.view.HeroView;

import java.util.ArrayList;
import java.util.List;

public class HeroStackPresenter {
  private final List<HeroIdentifier> knownCharacters = new ArrayList<>();
  private final ItemSystemModel model;
  private final HeroEnvironment heroEnvironment;
  private final HeroStackBridge bridge;

  public HeroStackPresenter(HeroStackBridge bridge, ItemSystemModel model, HeroEnvironment heroEnvironment) {
    this.bridge = bridge;
    this.model = model;
    this.heroEnvironment = heroEnvironment;
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
    HeroItemData hero = (HeroItemData) item.getItemData();
    HeroView heroView = bridge.addViewForHero(identifier, hero);
    present(hero, heroView);
  }

  private void present(HeroItemData hero, HeroView heroView) {
    new HeroPresenter(hero, heroView, heroEnvironment).initPresentation();
    hero.getChangeManagement().setClean();
  }
}
