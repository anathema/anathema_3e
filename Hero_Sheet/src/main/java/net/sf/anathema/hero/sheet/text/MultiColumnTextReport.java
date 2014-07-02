package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.hero.framework.reporting.ReportException;

public class MultiColumnTextReport {
  private final Document document;
  private final float[][] columns;
  private final ColumnText columnText;
  private int column = 0;
  private int status = ColumnText.START_COLUMN;
  private float y;

  public MultiColumnTextReport(Document document, PdfWriter writer) {
    this.document = document;
    this.columnText = new ColumnText(writer.getDirectContent());
    float middle = (document.left() + document.right()) / 2;
    this.columns = new float[][]{
            {document.left(), document.bottom(), middle - 15, document.top()},
            {middle + 15, document.bottom(), document.right(), document.top()}
    };
    columnText.setSimpleColumn(
            columns[column][0], columns[column][1],
            columns[column][2], columns[column][3]);
  }

  public void addElement(Element element) {
    columnText.addElement(element);
  }

  public void startSimulation() {
    y = columnText.getYLine();
  }

  public void simulateAndReset() {
    try {
      status = columnText.go(true);
      y = switchColumns();
      columnText.setYLine(y);
      columnText.setText(null);
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  private float switchColumns() {
    if (ColumnText.hasMoreText(status)) {
      column = (column + 1) % 2;
      if (column == 0)
        document.newPage();
      columnText.setSimpleColumn(
              columns[column][0], columns[column][1],
              columns[column][2], columns[column][3]);
      y = columns[column][3];
    }
    return y;
  }

  public void printForReal() {
    try {
      status = columnText.go();
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }
}