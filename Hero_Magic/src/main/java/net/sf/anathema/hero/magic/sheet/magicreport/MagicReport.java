package net.sf.anathema.hero.magic.sheet.magicreport;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.text.MultiColumnTextReport;

import java.util.Collection;

public class MagicReport extends AbstractPdfReport {

  private final MagicPartFactory partFactory;
  private final HeroEnvironment environment;

  public MagicReport(HeroEnvironment environment) {
    this.environment = environment;
    this.partFactory = new MagicPartFactory(new PdfReportUtils());
  }

  @Override
  public String toString() {
    return environment.getResources().getString("MagicReport.Name");
  }

  @Override
  public void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException {
    try {
      MultiColumnTextReport report = new MultiColumnTextReport(document, writer);
      Collection<MagicPrinter> printers = environment.getObjectFactory().instantiateAllImplementersOrdered(MagicPrinter.class, partFactory, environment);
      for (MagicPrinter printer : printers) {
        printer.print(report, hero);
      }
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  @Override
  public boolean supports(Hero hero) {
    Collection<MagicPrinter> printers = environment.getObjectFactory().instantiateAllImplementersOrdered(MagicPrinter.class, partFactory, environment);
    for (MagicPrinter printer : printers) {
      if (printer.hasData(hero)){
        return true;
      }
    }
    return false;
  }
}