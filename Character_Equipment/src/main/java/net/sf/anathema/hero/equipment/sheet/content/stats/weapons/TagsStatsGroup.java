package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableColumns;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableEncodingUtilities;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Artifact;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Balanced;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.CloseRange;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Flame;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.LongRange;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.MediumRange;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Natural;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Shield;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.ShortRange;

public final class TagsStatsGroup implements IEquipmentStatsGroup<IWeaponStats> {
  private final String title;
  private final Resources resources;
  private final List<WeaponTag> printedTags = Lists.newArrayList(Artifact, Shield, Balanced, CloseRange, ShortRange, MediumRange, LongRange, Flame, Natural);

  public TagsStatsGroup(Resources resources) {
    this.resources = resources;
    this.title = resources.getString("Sheet.Equipment.Header.Tags");
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public TableColumns getColumnWeights() {
    return TableColumns.singleColumn(1.7f);
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyNameCell(font));
    } else {
      Stream<Identifier> tags = weapon.getTags().stream();
      List<String> values = tags.filter(printedTags::contains).map(input -> resources.getString("Weapons.Tags." + input.getId() + ".Short")).collect(toList());
      String valueString = values.isEmpty() ? " " : Joiner.on(",").join(values);
      table.addCell(createFilledContentCell(font, valueString));
    }
  }

  private PdfPCell createEmptyNameCell(Font font) {
    return createFilledContentCell(font, " ");
  }

  private PdfPCell createFilledContentCell(Font font, String text) {
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, text, font, 0.5f, Rectangle.BOTTOM, Element.ALIGN_LEFT);
  }
}
