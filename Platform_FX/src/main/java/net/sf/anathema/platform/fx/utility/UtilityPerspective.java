package net.sf.anathema.platform.fx.utility;

import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.messaging.MessageCategory;
import net.sf.anathema.platform.utility.UtilityToggle;

public interface UtilityPerspective {

  void configureToggle(UtilityToggle toggle);

  void initContent(Container container, ApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment);

  MessageCategory getMessageCategory();
}
