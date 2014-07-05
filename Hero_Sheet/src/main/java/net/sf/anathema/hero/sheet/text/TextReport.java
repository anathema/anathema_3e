package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.Collection;

public class TextReport extends AbstractPdfReport {

  private final PdfReportUtils utils;
  private final HeroEnvironment environment;

  public TextReport(HeroEnvironment environment) {
    this.environment = environment;
    utils = new PdfReportUtils();
  }

  @Override
  public String toString() {
    return environment.getResources().getString("CharacterModule.Reporting.Text.Name");
  }

  @Override
  public void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException {
    try {
      MultiColumnTextReport report = new MultiColumnTextReport(document, writer);
      Collection<HeroTextEncoderFactory> encoderFactories = environment.getObjectFactory().instantiateOrdered(
        RegisteredTextEncoderFactory.class);
      for (HeroTextEncoderFactory factory : encoderFactories) {
        report.startSimulation();
        factory.create(utils, environment.getResources()).createParagraphs(report, hero);
        report.simulateAndReset();
        factory.create(utils, environment.getResources()).createParagraphs(report, hero);
        report.printForReal();
      }
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  @Override
  public boolean supports(Hero hero) {
    return true;
  }
}