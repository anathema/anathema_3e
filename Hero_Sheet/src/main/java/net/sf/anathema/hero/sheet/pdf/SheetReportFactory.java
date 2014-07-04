package net.sf.anathema.hero.sheet.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.hero.environment.report.RegisteredReportFactory;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportFactory;
import net.sf.anathema.hero.sheet.preferences.PageSizePreference;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;

import java.util.List;

@RegisteredReportFactory
@Weight(weight = 10)
public class SheetReportFactory implements ReportFactory {

  @Override
  public Report[] createReport(Environment environment, ApplicationModel model) {
    PageSizePreference pageSizePreference = new PageSizePreference(environment);
    List<AbstractPdfReport> reports = Lists.newArrayList(
            new PortraitSimpleExaltSheetReport(environment, pageSizePreference),
            new PortraitSimpleMortalSheetReport(environment, pageSizePreference),
            new LandscapeExaltSheetReport(environment, pageSizePreference));
    return reports.toArray(new Report[reports.size()]);
  }
}
