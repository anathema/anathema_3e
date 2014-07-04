package net.sf.anathema.hero.application.report;

import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.List;

public interface AllReportFinder {

  List<Report> getAllReports(Hero hero);
}
