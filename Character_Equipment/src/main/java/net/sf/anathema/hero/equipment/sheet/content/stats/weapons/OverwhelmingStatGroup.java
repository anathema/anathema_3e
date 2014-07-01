package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;

public class OverwhelmingStatGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> implements IEquipmentStatsGroup<IWeaponStats> {

  public OverwhelmingStatGroup(Resources resources) {
    super(resources, "Overwhelming");
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, weapon.getOverwhelmingValue()));
    }
  }
}
