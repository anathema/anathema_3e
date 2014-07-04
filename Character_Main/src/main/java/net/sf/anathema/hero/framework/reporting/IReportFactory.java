package net.sf.anathema.hero.framework.reporting;

import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

public interface IReportFactory {

  Report[] createReport(Environment environment, ApplicationModel model);
}
