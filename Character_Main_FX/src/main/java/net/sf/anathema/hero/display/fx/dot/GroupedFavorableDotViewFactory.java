package net.sf.anathema.hero.display.fx.dot;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;

@Produces(GroupedFavorableDotConfigurationView.class)
public class GroupedFavorableDotViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxGroupedDotConfigurationView fxView = new FxGroupedDotConfigurationView();
    return (T) fxView;
  }
}