package net.sf.anathema.hero.display.fx.configurableview;

import net.sf.anathema.hero.application.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.view.ConfigurableCharacterView;

@Produces(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxConfigurableView view = new FxConfigurableView();
    return (T) view;
  }
}