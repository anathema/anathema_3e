package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.library.fx.dot.ColumnCount;
import net.sf.anathema.library.fx.dot.GroupedFavorableDotConfigurationView;
import net.sf.anathema.library.resources.Resources;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(Hero hero, Resources resources, GroupedFavorableDotConfigurationView view) {
    AbilitiesModel abilitiesModel = AbilitiesModelFetcher.fetch(hero);
    IdentifiedTraitTypeList[] traitTypeGroups = abilitiesModel.getTraitTypeList();
    view.initGui(new ColumnCount(2));
    this.presenter = new FavorableTraitConfigurationPresenter(abilitiesModel, traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}