package net.sf.anathema.hero.application.report;

import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.individual.model.Hero;

public interface DefaultReportFinder {
  Report getDefaultReport(Hero hero);
}