package net.sf.anathema.scribe.perspective;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.perspective.Container;
import net.sf.anathema.platform.fx.perspective.UtilityPerspective;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.utility.UtilityAutoCollector;
import net.sf.anathema.platform.utility.UtilityToggle;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.presenter.ScribePresenter;
import net.sf.anathema.scribe.perspective.view.ScribeView;

@UtilityAutoCollector
@Weight(weight = 200)
public class ScribePerspective implements UtilityPerspective {

  @Override
  public void configureToggle(UtilityToggle toggle) {
    toggle.setIcon(new RelativePath("icons/Scroll20.png"));
    toggle.setTooltip("Scribe.Perspective.Name");
  }

  @Override
  public void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    ScribeView view = new ScribeView(uiEnvironment);
    ScribeModel scribeModel = new ScribeModel(applicationModel);
    new ScribePresenter(scribeModel, view, environment).initPresentation();
    container.setContent(view.utilityPane.getNode());
  }

  @Override
  public MessageCategory getMessageCategory() {
    return new MessageCategory("Scribe");
  }
}
