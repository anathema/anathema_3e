package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsDialog;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.library.resources.Resources;

public interface StatsEditor {
  void editStats(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog view);

  void whenChangesAreConfirmed(Closure<IEquipmentStats> action);
}