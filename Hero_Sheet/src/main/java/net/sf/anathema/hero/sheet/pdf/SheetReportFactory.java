package net.sf.anathema.hero.sheet.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.hero.sheet.preferences.PageSizePreference;
import net.sf.anathema.library.initialization.Weight;

import java.util.List;

@RegisteredReportFactory
@Weight(weight = 10)
public class SheetReportFactory implements ReportFactory {

  @Override
  public List<Report> createReport(HeroEnvironment environment) {
    PageSizePreference pageSizePreference = new PageSizePreference(environment);
    return Lists.newArrayList(
            new PortraitSimpleExaltSheetReport(environment, pageSizePreference),
            new PortraitSimpleMortalSheetReport(environment, pageSizePreference),
            new LandscapeExaltSheetReport(environment, pageSizePreference));
  }
}
