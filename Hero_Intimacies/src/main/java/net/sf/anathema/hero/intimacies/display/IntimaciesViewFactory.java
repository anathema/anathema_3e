package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.framework.display.SubViewFactory;

@Produces(IntimaciesView.class)
public class IntimaciesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxIntimaciesView();
  }
}