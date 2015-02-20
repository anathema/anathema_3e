package net.sf.anathema.equipment.stats;

import net.sf.anathema.library.identifier.Identifier;

public enum ArmourTag implements Identifier {
  Light, Medium, Heavy, Artifact, Flexible, Silent;

  @Override
  public String getId() {
    return name();
  }

  public static ArmourTag[] getSizeTags() {
    return new ArmourTag[]{Light, Medium, Heavy};
  }
}