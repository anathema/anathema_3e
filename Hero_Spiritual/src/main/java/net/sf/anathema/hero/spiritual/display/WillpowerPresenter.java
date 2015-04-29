package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.presenter.Presenter;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.IntValueView;

public class WillpowerPresenter implements Presenter {

  private final Trait willpower;
  private final SpiritualTraitsView view;
  private final Resources resources;

  public WillpowerPresenter(Resources resources, Trait willpower, SpiritualTraitsView view) {
    this.resources = resources;
    this.willpower = willpower;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    String labelText = resources.getString("WillpowerType.Name");
    IntValueView willpowerView = view.addWillpowerView(labelText, willpower.getMaximalValue());
    new TraitPresenter(willpower, willpowerView).initPresentation();
  }
}
