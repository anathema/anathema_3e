package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.library.resources.Resources;

public class DefenceWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private Hero hero;
  private final EquipmentHeroEvaluator provider;
  private EquipmentOptionsProvider optionProvider;

  public DefenceWeaponStatsGroup(Resources resources, Hero hero, EquipmentHeroEvaluator provider,
                                 EquipmentOptionsProvider optionProvider) {
    super(resources, "Defence");
    this.hero = hero;
    this.provider = provider;
    this.optionProvider = optionProvider;
  }

  @Override
  public int getColumnCount() {
    return 2;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createEquipmentValueCell(font, weapon.getDefence()));
      if (weapon.getDefence() == null) {
        table.addCell(createFinalValueCell(font, (Integer) null));
      } else {
        table.addCell(createFinalValueCell(font, getDefenceValue(weapon)));
      }
    }
  }

  protected int getDefenceValue(IWeaponStats weapon) {
    TraitMap traitCollection = TraitModelFetcher.fetch(hero);
    double finalValue = calculateFinalValue(weapon.getDefence() + getOptionModifiers(weapon), traitCollection.getTrait(AttributeType.Dexterity),
            traitCollection.getTrait(weapon.getTraitType()));
    boolean isMortal = !hero.getSplat().getTemplateType().getCharacterType().isEssenceUser();
    if (isMortal) {
      finalValue = Math.floor(finalValue / 2);
    } else {
      finalValue = Math.ceil(finalValue / 2);
    }
    return (int) finalValue;
  }

  private int getOptionModifiers(IWeaponStats stats) {
    if (provider == null) {
      return 0;
    }
    int mod = 0;
    for (IEquipmentStatsOption option : optionProvider.getEnabledStatOptions(stats)) {
      mod += option.getDefenseModifier();
    }
    return mod;
  }
}
