package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.library.identifier.Identifier;

public enum ArmourTag implements Identifier {
  Light, Medium, Heavy, Artifact;

  @Override
  public String getId() {
    return name();
  }

  public static ArmourTag[] getSizeTags() {
    return new ArmourTag[]{Light, Medium, Heavy};
  }
}