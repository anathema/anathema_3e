package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.platform.initialization.Produces;

@Produces(SpecialtiesConfigurationView.class)
public class SpecialtiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpecialtiesView();
  }
}