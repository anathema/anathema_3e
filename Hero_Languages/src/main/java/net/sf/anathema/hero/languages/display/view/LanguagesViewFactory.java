package net.sf.anathema.hero.languages.display.view;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.hero.languages.display.presenter.LanguagesView;
import net.sf.anathema.platform.initialization.Produces;

@Produces(LanguagesView.class)
public class LanguagesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxLanguagesView();
  }
}