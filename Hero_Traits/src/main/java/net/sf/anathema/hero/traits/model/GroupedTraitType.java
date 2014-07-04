package net.sf.anathema.hero.traits.model;


public class GroupedTraitType {

  private final TraitType type;
  private final String groupId;

  public GroupedTraitType(TraitType type, String groupId) {
    this.type = type;
    this.groupId = groupId;
  }

  public TraitType getTraitType() {
    return type;
  }

  public String getGroupId() {
    return groupId;
  }
}