package net.sf.anathema.hero.equipment.sheet.content.stats.armour;

import net.sf.anathema.equipment.stats.IArmourStats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

public interface IArmourStatsGroup {

  void addTotal(PdfPTable table, Font font, IArmourStats armour);
}
