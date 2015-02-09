package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSystemModel;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecentHeroesPresenter {

  private final ItemSystemModel model;
  private final UpdatingHeroesGridView view;
  private final Selector<HeroIdentifier> selector;
  private final Resources resources;
  private final List<CharacterButtonPresenterWithFeatureListening> activePresenters = new ArrayList<>();

  public RecentHeroesPresenter(ItemSystemModel model, UpdatingHeroesGridView view, Selector<HeroIdentifier> selector,
                               Resources resources) {
    this.model = model;
    this.view = view;
    this.selector = selector;
    this.resources = resources;
  }

  public void initPresentation() {
    model.whenGetsSelection(this::showAllHeroesAndSelect);
    showAllHeroes();
  }
  
  private void showAllHeroesAndSelect(){
    showAllHeroes();
    view.selectButton(model.getSelection());
  }

  private void showAllHeroes() {
    activePresenters.forEach(CharacterButtonPresenterWithFeatureListening::stopPresentation);
    activePresenters.clear();
    view.clearAllButtons();
    model.getMostRecentHeroes().forEach(this::present);
  }

  private void present(CharacterItemModel character) {
    CharacterButtonPresenterWithFeatureListening presenter = new CharacterButtonPresenterWithFeatureListening(resources, selector, character, view);
    presenter.initPresentation();
    activePresenters.add(presenter);
  }
}