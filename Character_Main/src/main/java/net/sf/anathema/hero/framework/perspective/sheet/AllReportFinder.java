package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.hero.framework.reporting.Report;
import net.sf.anathema.hero.model.Hero;

import java.util.List;

public interface AllReportFinder {

  List<Report> getAllReports(Hero hero);
}
