package net.sf.anathema.framework.reporting.pdf;

import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.individual.model.Hero;

import java.io.OutputStream;

public abstract class AbstractPdfReport implements Report, PdfReport {

  @Override
  public final void print(String name, Hero hero, OutputStream stream) throws ReportException {
    PdfReportPrinter printer = new PdfReportPrinter();
    printer.printReport(name, hero, this, stream);
  }
}
