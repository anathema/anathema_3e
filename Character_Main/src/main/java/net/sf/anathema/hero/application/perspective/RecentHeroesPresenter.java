package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSystemModel;
import net.sf.anathema.library.resources.Resources;

public class RecentHeroesPresenter {

  private final ItemSystemModel model;
  private final UpdatingHeroesGridView view;
  private final Selector<HeroIdentifier> selector;
  private final Resources resources;

  public RecentHeroesPresenter(ItemSystemModel model, UpdatingHeroesGridView view, Selector<HeroIdentifier> selector,
                               Resources resources) {
    this.model = model;
    this.view = view;
    this.selector = selector;
    this.resources = resources;
  }

  public void initPresentation() {
    for (CharacterItemModel character : model.collectAllExistingCharacters()) {
      initPresentation(character);
    }
  }

  private void initPresentation(CharacterItemModel character) {
    new CharacterButtonPresenterWithFeatureListening(resources, selector, character, view).initPresentation();
  }
}