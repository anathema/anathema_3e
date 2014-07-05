package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.individual.model.Hero;

import java.io.OutputStream;

public class PdfReportPrinter {

  public void printReport(String name, Hero hero, PdfReport report, OutputStream outputStream) throws ReportException {
    Document document = new Document();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      writer.setPdfVersion(PdfWriter.VERSION_1_5);
      writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
      document.addTitle(name);
      document.addCreator("Anathema");
      document.open();
      report.performPrint(hero, document, writer);
    } catch (DocumentException de) {
      throw new ReportException(de);
    }
    document.close();
  }
}
