package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.hero.framework.reporting.IReportFactory;
import net.sf.anathema.hero.framework.reporting.Report;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.report.RegisteredReportFactory;

@RegisteredReportFactory
@Weight(weight = 30)
public class MagicReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, ApplicationModel model) {
    return new Report[]{new MagicReport(environment, model)};
  }
}
