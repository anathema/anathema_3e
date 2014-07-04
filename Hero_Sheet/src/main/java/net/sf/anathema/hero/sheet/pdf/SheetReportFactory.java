package net.sf.anathema.hero.sheet.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.hero.framework.reporting.IReportFactory;
import net.sf.anathema.hero.framework.reporting.Report;
import net.sf.anathema.hero.sheet.preferences.PageSizePreference;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.report.RegisteredReportFactory;

import java.util.List;

@RegisteredReportFactory
@Weight(weight = 10)
public class SheetReportFactory implements IReportFactory {

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
