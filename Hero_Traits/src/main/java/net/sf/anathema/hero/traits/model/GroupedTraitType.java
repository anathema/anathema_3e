package net.sf.anathema.hero.traits.model;


import net.sf.anathema.hero.traits.model.group.GroupName;

public class GroupedTraitType {

  private final TraitType type;
  private final GroupName group;

  public GroupedTraitType(TraitType type, GroupName group) {
    this.type = type;
    this.group = group;
  }

  public TraitType getTraitType() {
    return type;
  }

  public GroupName getGroupName() {
    return group;
  }
}