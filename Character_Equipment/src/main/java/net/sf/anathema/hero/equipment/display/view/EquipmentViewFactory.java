package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.library.fx.Stylesheet;

@Produces(EquipmentView.class)
public class EquipmentViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxEquipmentView fxView = new FxEquipmentView();
    new Stylesheet("skin/platform/tooltip.css").applyToParent(fxView.getNode());
    new Stylesheet("skin/equipment/items.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}