package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.library.initialization.Weight;

import java.util.Collections;
import java.util.List;

@RegisteredReportFactory
@Weight(weight = 30)
public class MagicReportFactory implements ReportFactory {

  @Override
  public List<Report> createReport(HeroEnvironment environment) {
    return Collections.singletonList(new MagicReport(environment));
  }
}
