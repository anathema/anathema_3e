package net.sf.anathema.hero.sheet.pdf.encoder.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.sheet.pdf.content.stats.IStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.IStatsGroup;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableCell;
import net.sf.anathema.hero.sheet.pdf.encoder.table.AbstractTableEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableColumns;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableEncodingUtilities;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractStatsTableEncoder<T extends IStats, C> extends AbstractTableEncoder<C> {

  private final boolean suppressHeaderLine;

  public AbstractStatsTableEncoder() {
    this(false);
  }

  public AbstractStatsTableEncoder(boolean suppressHeaderLine) {
    this.suppressHeaderLine = suppressHeaderLine;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, C content, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(content);
    TableColumns columns = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columns.asArray());
    table.setTotalWidth(bounds.width);
    if (!suppressHeaderLine) {
      encodeHeaderLine(graphics, table, groups);
    }
    encodeContent(graphics, table, content, bounds);
    return table;
  }

  protected abstract void encodeContent(SheetGraphics graphics, PdfPTable table, C content, Bounds bounds);

  protected abstract IStatsGroup<T>[] createStatsGroups(C content);

  protected final void encodeHeaderLine(SheetGraphics graphics, PdfPTable table, IStatsGroup<T>[] groups) {
    for (int index = 0; index < groups.length; index++) {
      Font usedFont = index == 0 ? createFont(graphics) : createHeaderFont(graphics);
      table.addCell(createHeaderCell(groups[index].getTitle(), groups[index].getColumnCount(), index != groups.length - 1, usedFont));
    }
  }

  protected final void encodeContentLine(SheetGraphics graphics, PdfPTable table, IStatsGroup<T>[] groups, T stats) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell(graphics));
      }
      groups[index].addContent(table, createFont(graphics), stats);
    }
  }

  protected final void encodeSectionLine(SheetGraphics graphics, PdfPTable table, String sectionName) {
    int columnCount = table.getAbsoluteWidths().length;
    Phrase phrase = new Phrase(sectionName, createSectionFont(graphics));
    PdfPCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPaddingTop(3);
    cell.setPaddingLeft(0.75f);
    cell.setColspan(columnCount);
    table.addCell(cell);
  }

  private TableColumns calculateColumnWidths(IStatsGroup<T>[] groups) {
    TableColumns tableColumns = new TableColumns();
    for (IStatsGroup<T> group : groups) {
      if (!tableColumns.isEmpty()) {
        tableColumns.add(0.2f);
      }
      tableColumns.adopt(group.getColumnWeights());
    }
    return tableColumns;
  }

  protected PdfPCell createSpaceCell(SheetGraphics graphics) {
    PdfPCell cell = new PdfPCell(new Phrase("", createFont(graphics)));
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private PdfPCell createHeaderCell(String text, int columnSpan, boolean useSpaceCell, Font textFont) {
    PdfPCell cell = new PdfPCell(new Phrase(text, textFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(useSpaceCell ? columnSpan + 1 : columnSpan);
    cell.setPaddingLeft(0);
    cell.setPaddingRight(0);
    cell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    return cell;
  }

  private Font createSectionFont(SheetGraphics graphics) {
    Font sectionFont = createFont(graphics);
    sectionFont.setStyle(Font.BOLD);
    return sectionFont;
  }

  protected final Font createFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  private Font createHeaderFont(SheetGraphics graphics) {
    return TableEncodingUtilities.createHeaderFont(graphics.getBaseFont());
  }
}
