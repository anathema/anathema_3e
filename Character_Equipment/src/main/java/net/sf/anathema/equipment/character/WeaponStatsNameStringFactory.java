package net.sf.anathema.equipment.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

public class WeaponStatsNameStringFactory {

  private final Resources resources;

  public WeaponStatsNameStringFactory(Resources resources) {
    this.resources = resources;
  }

  public String create(IEquipmentItem item, IWeaponStats stats) {
    StringBuilder builder = new StringBuilder(stats.getName().getId());
    if (hasMultipleViews(item, stats)) {
      builder.append(" (");
      builder.append(resources.getString(stats.getTraitType().getId()));
      builder.append(")");
    }
    return builder.toString();
  }

  @SuppressWarnings("SimplifiableIfStatement")
  private boolean hasMultipleViews(IEquipmentItem item, IWeaponStats stats) {
    if (item == null) {
      return true;
    }
    return Collections.frequency(getStatNames(item, new ArrayList<>()), stats.getName()) > 1;
  }

  private Collection<Identifier> getStatNames(IEquipmentItem item, Collection<Identifier> names) {
    for (IEquipmentStats stats : item.getStats()) {
      names.add(stats.getName());
    }
    return names;
  }
}