package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.fx.Stylesheet;

@Produces(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxCasteView fxView = new FxCasteView();
    new Stylesheet("skin/concept/concept.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}