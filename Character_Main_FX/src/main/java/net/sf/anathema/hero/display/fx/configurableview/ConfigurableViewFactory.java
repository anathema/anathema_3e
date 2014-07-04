package net.sf.anathema.hero.display.fx.configurableview;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.framework.display.SubViewFactory;

@Produces(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxConfigurableView view = new FxConfigurableView();
    return (T) view;
  }
}