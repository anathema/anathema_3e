package net.sf.anathema.hero.equipment.sheet.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;

public class SoakArmourStatsGroup extends AbstractValueEquipmentStatsGroup<IArmourStats> implements IArmourStatsGroup {

  private String valuePrefix = "";

  public SoakArmourStatsGroup(Resources resources) {
    super(resources, "Soak");
  }

  @Override
  public void addContent(PdfPTable table, Font font, IArmourStats armour) {
    if (armour == null) {
      table.addCell(createEmptyValueCell(font));
    } else {
      table.addCell(createEquipmentValueCell(font, armour.getSoak()));
    }
    valuePrefix = "+";
  }

  @Override
  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(createFinalValueCell(font, armour.getSoak()));
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  protected String getPositivePrefix() {
    return valuePrefix;
  }

  @Override
  protected String getZeroPrefix() {
    return valuePrefix;
  }
}
