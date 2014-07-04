package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.hero.application.SubViewFactory;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.fx.Stylesheet;

@Produces(ComboConfigurationView.class)
public class ComboViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxComboConfigurationView fxView = new FxComboConfigurationView();
    new Stylesheet("skin/combos/combos.css").applyToParent(fxView.getNode());
    new Stylesheet("skin/platform/tooltip.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}