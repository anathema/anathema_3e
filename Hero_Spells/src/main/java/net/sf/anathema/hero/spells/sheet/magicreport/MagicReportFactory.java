package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.library.initialization.Weight;

@RegisteredReportFactory
@Weight(weight = 30)
public class MagicReportFactory implements ReportFactory {

  @Override
  public Report[] createReport(HeroEnvironment environment) {
    return new Report[]{new MagicReport(environment)};
  }
}
