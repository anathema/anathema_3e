package net.sf.anathema.hero.spells.display.view;

import net.sf.anathema.hero.application.SubViewFactory;
import net.sf.anathema.hero.spells.display.presenter.SpellView;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.fx.Stylesheet;

@Produces(SpellView.class)
public class SpellViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxSpellView fxView = new FxSpellView();
    new Stylesheet("skin/platform/tooltip.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}