package net.sf.anathema.hero.display.fx.configurableview;

import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;

@Produces(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxConfigurableView view = new FxConfigurableView();
    return (T) view;
  }
}