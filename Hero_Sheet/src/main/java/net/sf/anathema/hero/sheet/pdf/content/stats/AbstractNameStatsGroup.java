package net.sf.anathema.hero.sheet.pdf.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableColumns;
import net.sf.anathema.library.resources.Resources;

public abstract class AbstractNameStatsGroup<T extends IStats> extends AbstractTextStatsGroup<T> {
  private final String title;
  private final Resources resources;

  public AbstractNameStatsGroup(Resources resources) {
    this.resources = resources;
    this.title = resources.getString(getHeaderResourceKey());
  }

  @Override
  public final String getTitle() {
    return title;
  }

  @Override
  public TableColumns getColumnWeights() {
    return TableColumns.singleColumn(6);
  }

  @Override
  public void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, ""));
    } else {
      String resourceKey = getResourceBase() + stats.getName().getId();
      table.addCell(createTextCell(font, resources.getString(resourceKey)));
    }
  }

  protected abstract String getHeaderResourceKey();

  protected abstract String getResourceBase();
}
