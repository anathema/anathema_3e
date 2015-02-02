package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSystemModel;
import net.sf.anathema.library.resources.Resources;

public class HeroSystemPresenter {

  private final ItemSystemModel model;
  private final HeroesGridView view;
  private final Selector<HeroIdentifier> selector;
  private final Resources resources;

  public HeroSystemPresenter(ItemSystemModel model, HeroesGridView view, Selector<HeroIdentifier> selector,
                             Resources resources) {
    this.model = model;
    this.view = view;
    this.selector = selector;
    this.resources = resources;
  }

  public void initPresentation() {
    for (final CharacterItemModel character : model.collectAllExistingCharacters()) {
      initPresentation(character);
    }
  }

  private void initPresentation(final CharacterItemModel character) {
    new CharacterButtonPresenter(resources, selector, character, view).initPresentation();
  }
}