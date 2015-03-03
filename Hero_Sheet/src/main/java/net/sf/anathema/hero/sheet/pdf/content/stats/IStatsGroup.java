package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.sheet.pdf.encoder.table.TableColumns;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

public interface IStatsGroup<T extends IStats> {

  int getColumnCount();

  String getTitle();

  TableColumns getColumnWeights();

  void addContent(PdfPTable table, Font font, T stats);
}
