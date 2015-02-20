package net.sf.anathema.equipment.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.WeaponStatsNameStringFactory;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.resources.Resources;

import com.google.common.base.Joiner;

public class EquipmentStringBuilder implements IEquipmentStringBuilder {

  private final Resources resources;

  public EquipmentStringBuilder(Resources resources) {
    this.resources = resources;
  }

  private String createWeaponString(IEquipmentItem item, IWeaponStats weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    String key = IEquipmentObjectPresenter.EQUIPMENT_NAME_PREFIX + weapon.getName().getId();
    if (resources.supportsKey(key)) {
      stringBuilder.append(resources.getString(key));
    } else {
      stringBuilder.append(new WeaponStatsNameStringFactory(resources).create(item, weapon));
    }
    stringBuilder.append(": ");
    stringBuilder.append(getTagsString(weapon.getTags()));
    return stringBuilder.toString();
  }

  private String getStatsString(String keyPart, Integer value, boolean printSignum) {
    if (value == null) {
      return "";
    }
    String signum = printSignum && value >= 0 ? "+" : "";
    return createtNewStatsStart(keyPart) + signum + value;
  }

  private String getTagsString(Collection<Identifier> tags) {
    List<String> ids = new ArrayList<>();
    tags.forEach(tag -> ids.add(tag.getId()));
    return Joiner.on(", ").join(ids);
  }

  private String createtNewStatsStart(String keyPart) {
    return " " + resources.getString("Equipment.Stats.Short." + keyPart) + ":";
  }

  @Override
  public String createString(IEquipmentItem item, IEquipmentStats equipment) {
    if (equipment instanceof IWeaponStats) {
      return createWeaponString(item, (IWeaponStats) equipment);
    }
    if (equipment instanceof IArmourStats) {
      return createArmourString((IArmourStats) equipment);
    }
    if (equipment instanceof IArtifactStats) {
      return createArtifactString((IArtifactStats) equipment);
    }
    throw new IllegalArgumentException("All subclasses covered. Something appears to be wrong.");
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  private String createArtifactString(IArtifactStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getStatsString("Attuned", stats.getAttuneCost(), false));
    stringBuilder.append("m");
    return stringBuilder.toString();
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  private String createArmourString(IArmourStats armourStats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(armourStats.getName().getId());
    stringBuilder.append(": ");
    stringBuilder.append(getTagsString(armourStats.getTags()));
    return stringBuilder.toString();
  }
}