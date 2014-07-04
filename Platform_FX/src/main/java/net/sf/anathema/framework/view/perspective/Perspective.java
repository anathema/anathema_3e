package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.perspective.PerspectiveToggle;

public interface Perspective {

  void configureToggle(PerspectiveToggle toggle);

  void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment);
}
