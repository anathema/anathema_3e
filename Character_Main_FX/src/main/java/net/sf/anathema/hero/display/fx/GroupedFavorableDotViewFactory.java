package net.sf.anathema.hero.display.fx;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.fx.dot.FxGroupedDotConfigurationView;
import net.sf.anathema.library.fx.dot.GroupedStatedDotsView;

@Produces(GroupedStatedDotsView.class)
public class GroupedFavorableDotViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxGroupedDotConfigurationView fxView = new FxGroupedDotConfigurationView();
    return (T) fxView;
  }
}