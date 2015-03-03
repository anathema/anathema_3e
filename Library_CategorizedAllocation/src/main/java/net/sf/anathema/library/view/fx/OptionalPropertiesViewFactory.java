package net.sf.anathema.library.view.fx;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.library.view.property.OptionalPropertiesView;

@Produces(OptionalPropertiesView.class)
public class OptionalPropertiesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxOptionalPropertiesView();
  }
}