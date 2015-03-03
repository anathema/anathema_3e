package net.sf.anathema.framework.reporting.pdf;

import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.environment.report.ReportException;
import net.sf.anathema.hero.individual.model.Hero;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public interface PdfReport extends Report {

  void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException;
}