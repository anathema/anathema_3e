package net.sf.anathema.hero.display.fx.dot;

import net.sf.anathema.hero.application.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;

@Produces(GroupedFavorableDotConfigurationView.class)
public class GroupedFavorableDotViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxGroupedDotConfigurationView fxView = new FxGroupedDotConfigurationView();
    return (T) fxView;
  }
}