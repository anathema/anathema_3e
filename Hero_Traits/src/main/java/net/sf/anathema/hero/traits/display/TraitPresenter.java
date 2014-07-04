package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.view.IntValueView;

public class TraitPresenter {

  private final Trait trait;
  private final IntValueView view;

  public TraitPresenter(Trait trait, IntValueView view) {
    this.trait = trait;
    this.view = view;
  }

  public void initPresentation() {
    view.setValue(trait.getCurrentValue());
    initModelValueListening();
    initViewValueListening();
  }

  private void initModelValueListening() {
    trait.addCurrentValueListener(view::setValue);
  }

  private void initViewValueListening() {
    view.addIntValueChangedListener(trait::setCurrentValue);
  }
}
