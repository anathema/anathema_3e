package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.library.resources.Resources;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

public class DeceptionStatsGroup extends AbstractSocialCombatsValueStatsGroup {

  public DeceptionStatsGroup(Resources resources) {
    super(resources, "Deception");
  }

  @Override
  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, stats.getDeceptionAttackValue()));
    }
  }

  @Override
  public int getColumnCount() {
    return 2;
  }
}
