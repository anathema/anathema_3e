package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.equipment.stats.impl.ArmourStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class GsonStatMother {

  public static WeaponStats createMeleeWeapon() {
    WeaponStats stats = new WeaponStats();
    stats.setName(new SimpleIdentifier("Chaaarge!"));
    return stats;
  }

  public static ArtifactStats createArtifact() {
    ArtifactStats stats = new ArtifactStats();
    stats.setName(new SimpleIdentifier("Zing!"));
    stats.setAttuneCost(5);
    return stats;
  }

  public static ArmourStats createArmour() {
    ArmourStats stats = new ArmourStats();
    stats.setName(new SimpleIdentifier("Bounce!"));
    return stats;
  }
}