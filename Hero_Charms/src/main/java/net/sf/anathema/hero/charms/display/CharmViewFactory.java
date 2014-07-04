package net.sf.anathema.hero.charms.display;

import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.FxCharmView;
import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.library.view.BooleanView;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.platform.initialization.Produces;

@Produces(CharmView.class)
public class CharmViewFactory implements SubViewFactory {
  //The special types are registered here so cascades don't need a character type as well.
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxCharmView fxView = new FxCharmView();
    fxView.registerSpecialType(IntValueView.class, new FxIntDisplayFactory());
    fxView.registerSpecialType(BooleanView.class, new FxBooleanDisplayFactory());
    new Stylesheet("skin/platform/tooltip.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}
