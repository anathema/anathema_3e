package net.sf.anathema.hero.environment.report;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.dependencies.IsAutoCollected;

@IsAutoCollected // via annotation @RegisteredReportFactory
public interface ReportFactory {

  Report[] createReport(HeroEnvironment environment);
}
