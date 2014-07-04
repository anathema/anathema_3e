package net.sf.anathema.hero.abilities.display;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.display.fx.traitview.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.hero.framework.display.ColumnCount;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.display.FavorableTraitConfigurationPresenter;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;

public class AbilitiesPresenter {

  private final FavorableTraitConfigurationPresenter presenter;

  public AbilitiesPresenter(Hero hero, Resources resources, GroupedFavorableTraitConfigurationView view) {
    IdentifiedTraitTypeList[] traitTypeGroups = AbilityModelFetcher.fetch(hero).getTraitTypeList();
    view.initGui(new ColumnCount(2));
    this.presenter = new FavorableTraitConfigurationPresenter(traitTypeGroups, hero, view, resources);
  }

  public void initPresentation() {
    presenter.init("AbilityGroup");
  }
}