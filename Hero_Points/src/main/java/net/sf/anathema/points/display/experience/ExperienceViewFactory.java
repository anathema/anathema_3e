package net.sf.anathema.points.display.experience;

import net.sf.anathema.hero.framework.display.SubViewFactory;
import net.sf.anathema.library.fx.Stylesheet;
import net.sf.anathema.platform.initialization.Produces;

@Produces(ExperienceView.class)
public class ExperienceViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxExperienceView fxView = new FxExperienceView();
    new Stylesheet("skin/experience/experience.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}