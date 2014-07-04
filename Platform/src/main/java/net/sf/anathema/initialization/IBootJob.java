package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.platform.environment.Environment;

public interface IBootJob {

  void run(Environment environment, IApplicationModel model);
}