package net.sf.anathema.hero.equipment.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;

public class EquipmentItemPresentationModel {

  private final Map<IEquipmentStats, StatsView> attuneStatFlags = new HashMap<>();
  private final Map<IEquipmentStats, StatsView> otherStatFlags = new HashMap<>();

  public void clear() {
    attuneStatFlags.clear();
    otherStatFlags.clear();
  }

  public void registerViewForAttunementStats(IEquipmentStats equipment, StatsView booleanModel) {
    attuneStatFlags.put(equipment, booleanModel);
  }

  public void registerViewForDefaultStats(IEquipmentStats equipment, StatsView booleanModel) {
    otherStatFlags.put(equipment, booleanModel);
  }

  public Iterable<IEquipmentStats> getAttunementStats() {
    return attuneStatFlags.keySet();
  }

  public Iterable<StatsView> getDefaultStatViews() {
    return otherStatFlags.values();
  }
}
