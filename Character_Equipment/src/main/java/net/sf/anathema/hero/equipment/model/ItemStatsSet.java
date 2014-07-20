package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemStatsSet implements Iterable<IEquipmentStats> {
  public static ItemStatsSet withSingleStat(IEquipmentStats stats) {
    return from(stats);
  }

  public static ItemStatsSet from(IEquipmentStats... stats) {
    ItemStatsSet statsSet = new ItemStatsSet();
    for (IEquipmentStats stat : stats) {
      statsSet.add(stat);
    }
    return statsSet;
  }

  private List<IEquipmentStats> list;

  public ItemStatsSet() {
    this(new ArrayList<>());
  }

  public ItemStatsSet(List<IEquipmentStats> statses) {
    this.list = statses;
  }

  public void add(IEquipmentStats stats) {
    list.add(stats);
  }

  public void adopt(ItemStatsSet statsSet) {
    list.addAll(statsSet.list);
  }

  @Override
  public Iterator<IEquipmentStats> iterator() {
    return list.iterator();
  }

  public boolean hasOnlyOneStat() {
    return list.size() == 1;
  }

  public ItemStatsSet apply(Function<IEquipmentStats, IEquipmentStats> modifier) {
    Stream<IEquipmentStats> stats = list.stream();
    List<IEquipmentStats> modifiedStats = stats.map(modifier).collect(Collectors.toList());
    return new ItemStatsSet(modifiedStats);
  }
}