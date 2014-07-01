package net.sf.anathema.hero.equipment.display.presenter;

import com.google.common.base.Joiner;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.ITraitModifyingStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquipmentStringBuilder implements IEquipmentStringBuilder {

  private final Resources resources;

  public EquipmentStringBuilder(Resources resources) {
    this.resources = resources;
  }

  private String createWeaponString(IEquipmentItem item, IWeaponStats weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    String key = EquipmentObjectPresenter.EQUIPMENT_NAME_PREFIX + weapon.getName().getId();
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

  private String getTagsString(Identifier[] tags) {
    List<String> ids = new ArrayList<>();
    Arrays.asList(tags).forEach(tag -> ids.add(tag.getId()));
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
    if (equipment instanceof ArtifactStats) {
      return createArtifactString((ArtifactStats) equipment);
    }
    if (equipment instanceof ITraitModifyingStats) {
      return createTraitModifyingString((ITraitModifyingStats) equipment);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong.");
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  private String createArtifactString(ArtifactStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getStatsString(stats.getAttuneType().name(), stats.getAttuneCost(), false));
    stringBuilder.append("m");
    return stringBuilder.toString();
  }

  private String createTraitModifyingString(ITraitModifyingStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(stats.getName().getId());
    stringBuilder.append(":");
    if (stats.getDDVPoolMod() != 0)
      stringBuilder.append(getStatsString("DDV", stats.getDDVPoolMod(), true));
    if (stats.getPDVPoolMod() != 0)
      stringBuilder.append(getStatsString("PDV", stats.getPDVPoolMod(), true));
    if (stats.getMDDVPoolMod() != 0)
      stringBuilder.append(getStatsString("MDDV", stats.getMDDVPoolMod(), true));
    if (stats.getMPDVPoolMod() != 0)
      stringBuilder.append(getStatsString("MPDV", stats.getMPDVPoolMod(), true));
    if (stats.getMeleeSpeedMod() != 0)
      stringBuilder.append(getStatsString("MeleeSpeed", stats.getMeleeSpeedMod(), true));
    if (stats.getMeleeAccuracyMod() != 0)
      stringBuilder.append(getStatsString("MeleeAccuracy", stats.getMeleeAccuracyMod(), true));
    if (stats.getMeleeDamageMod() != 0)
      stringBuilder.append(getStatsString("MeleeDamage", stats.getMeleeDamageMod(), true));
    if (stats.getMeleeRateMod() != 0)
      stringBuilder.append(getStatsString("MeleeRate", stats.getMeleeRateMod(), true));
    if (stats.getRangedSpeedMod() != 0)
      stringBuilder.append(getStatsString("RangedSpeed", stats.getRangedSpeedMod(), true));
    if (stats.getRangedAccuracyMod() != 0)
      stringBuilder.append(getStatsString("RangedAccuracy", stats.getRangedAccuracyMod(), true));
    if (stats.getRangedDamageMod() != 0)
      stringBuilder.append(getStatsString("RangedDamage", stats.getRangedDamageMod(), true));
    if (stats.getRangedRateMod() != 0)
      stringBuilder.append(getStatsString("RangedRate", stats.getRangedRateMod(), true));
    if (stats.getJoinBattleMod() != 0)
      stringBuilder.append(getStatsString("JoinBattle", stats.getJoinBattleMod(), true));
    if (stats.getJoinDebateMod() != 0)
      stringBuilder.append(getStatsString("JoinDebate", stats.getJoinDebateMod(), true));
    if (stats.getJoinWarMod() != 0)
      stringBuilder.append(getStatsString("JoinWar", stats.getJoinWarMod(), true));
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