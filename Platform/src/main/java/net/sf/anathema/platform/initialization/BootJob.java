package net.sf.anathema.platform.initialization;

import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public interface BootJob {

  void run(Environment environment, ApplicationModel model);
}