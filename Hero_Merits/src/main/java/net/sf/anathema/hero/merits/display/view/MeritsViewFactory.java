package net.sf.anathema.hero.merits.display.view;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.hero.merits.display.presenter.MeritsView;
import net.sf.anathema.library.dependencies.Produces;

@Produces(MeritsView.class)
public class MeritsViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxMeritsView();
  }
}