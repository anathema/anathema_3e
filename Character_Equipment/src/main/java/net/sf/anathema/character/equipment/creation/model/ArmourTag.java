package net.sf.anathema.character.equipment.creation.model;

public enum ArmourTag {
  Light, Medium, Heavy, Artifact;

  public String getId() {
    return name();
  }

  public static ArmourTag[] getSizeTags() {
    return new ArmourTag[]{Light, Medium, Heavy};
  }
}