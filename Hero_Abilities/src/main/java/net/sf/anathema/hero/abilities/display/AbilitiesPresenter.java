package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.display.StatedTraitModelPresenter;
import net.sf.anathema.library.fx.dot.ColumnCount;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;
import net.sf.anathema.library.resources.Resources;

public class AbilitiesPresenter {

  private final StatedTraitModelPresenter presenter;

  public AbilitiesPresenter(Hero hero, Resources resources, GroupedStatedDotsView view) {
    AbilitiesModel abilitiesModel = AbilitiesModelFetcher.fetch(hero);
    view.initGui(new ColumnCount(3));
    this.presenter = new StatedTraitModelPresenter(hero, abilitiesModel, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}