package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.platform.environment.Environment;

public interface Perspective {

  void configureToggle(PerspectiveToggle toggle);

  void initContent(Container container, IApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment);
}
