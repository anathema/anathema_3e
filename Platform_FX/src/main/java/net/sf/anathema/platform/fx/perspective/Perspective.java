package net.sf.anathema.platform.fx.perspective;

import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.messaging.MessageCategory;

public interface Perspective {

  void initContent(Container container, ApplicationModel applicationModel, Environment environment,
                   UiEnvironment uiEnvironment);

  MessageCategory getMessageCategory();
}
