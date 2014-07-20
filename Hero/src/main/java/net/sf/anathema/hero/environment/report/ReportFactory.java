package net.sf.anathema.hero.environment.report;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.library.dependencies.IsAutoCollected;

import java.util.List;

@IsAutoCollected // via annotation @RegisteredReportFactory
public interface ReportFactory {

  List<Report> createReport(HeroEnvironment environment);
}
