package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.character.equipment.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.equipment.character.model.stats.WeaponStats;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class GsonStatMother {

  public static WeaponStats createMeleeWeapon() {
    WeaponStats stats = new WeaponStats();
    stats.setName(new SimpleIdentifier("Chaaarge!"));
    return stats;
  }

  public static ArtifactStats createArtifact() {
    ArtifactStats stats = new ArtifactStats();
    stats.setName(new SimpleIdentifier("Zing!"));
    stats.setAllowForeignAttunement(true);
    stats.setAttuneCost(5);
    stats.setRequireAttunement(false);
    return stats;
  }

  public static ArmourStats createArmour() {
    ArmourStats stats = new ArmourStats();
    stats.setName(new SimpleIdentifier("Bounce!"));
    return stats;
  }

  public static TraitModifyingStats createTraitModifier() {
    TraitModifyingStats stats = new TraitModifyingStats();
    stats.setName(new SimpleIdentifier("Liftoff!"));
    stats.setDDVPoolMod(1);
    stats.setJoinBattleMod(2);
    stats.setJoinDebateMod(3);
    stats.setJoinWarMod(4);
    stats.setMDDVPoolMod(5);
    stats.setMeleeAccuracyMod(6);
    stats.setMeleeDamageMod(7);
    stats.setMeleeRateMod(8);
    stats.setMeleeSpeedMod(9);
    stats.setMPDVPoolMod(10);
    stats.setPDVPoolMod(11);
    stats.setRangedAccuracyMod(12);
    stats.setRangedDamageMod(13);
    stats.setRangedRateMod(14);
    stats.setRangedSpeedMod(15);
    return stats;
  }
}