package net.sf.anathema.hero.display.fx.traitview;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;

@Produces(GroupedFavorableTraitConfigurationView.class)
public class GroupedFavorableTraitViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxGroupedTraitConfigurationView fxView = new FxGroupedTraitConfigurationView();
    return (T) fxView;
  }
}