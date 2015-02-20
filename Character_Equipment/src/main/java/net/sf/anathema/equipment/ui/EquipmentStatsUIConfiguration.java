package net.sf.anathema.equipment.ui;

import net.sf.anathema.equipment.core.EquipmentStringBuilder;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ITraitModifyingStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;

public class EquipmentStatsUIConfiguration extends AbstractUIConfiguration<IEquipmentStats> {
  private final EquipmentStringBuilder tooltipFactory;

  public EquipmentStatsUIConfiguration(Resources resources) {
    this.tooltipFactory = new EquipmentStringBuilder(resources);
  }

  @Override
  protected RelativePath iconForExistingValue(IEquipmentStats value) {
    if (value instanceof IWeaponStats) {
      if (((IWeaponStats) value).isRangedCombat()) {
        return new RelativePath("icons/RangedCombat16.png");
      }
      return new RelativePath("icons/CloseCombat16.png");
    }
    if (value instanceof IArmourStats) {
      return new RelativePath("icons/Armor16.png");
    }
    if (value instanceof ArtifactStats) {
      return new RelativePath("icons/Artifact16.png");
    }
    if (value instanceof ITraitModifyingStats) {
      return new RelativePath("icons/TraitModifying16.png");
    }
    throw new IllegalArgumentException("All subclasses covered. Something appears to be wrong.");
  }

  @Override
  protected String labelForExistingValue(IEquipmentStats value) {
    return value.getName().getId();
  }


  @Override
  protected void configureTooltipForExistingValue(IEquipmentStats value, ConfigurableTooltip configurableTooltip) {
    configurableTooltip.appendLine(tooltipFactory.createString(null, value));
  }
}