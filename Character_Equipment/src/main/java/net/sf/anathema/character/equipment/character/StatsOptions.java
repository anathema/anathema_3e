package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatsOptions implements Iterable<IEquipmentStatsOption>{
  private List<IEquipmentStatsOption> list = new ArrayList<>();

  public StatsOptions() {
    this(new ArrayList<>());
  }

  public StatsOptions(List<IEquipmentStatsOption> options) {
    this.list = options;
  }

  @Override
  public Iterator<IEquipmentStatsOption> iterator() {
    return list.iterator();
  }
}
