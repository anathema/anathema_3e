package net.sf.anathema.hero.environment.report;

import net.sf.anathema.library.autocollect.IsAutoCollected;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

@IsAutoCollected // via annotation @RegisteredReportFactory
public interface ReportFactory {

  Report[] createReport(Environment environment, ApplicationModel model);
}
