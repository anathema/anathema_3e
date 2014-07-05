package net.sf.anathema.hero.traits.model.group;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.TraitTypeList;
import net.sf.anathema.hero.traits.template.Group;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;

public class GroupedTraitTypeBuilder {

  public static GroupedTraitType[] BuildFor(GroupedTraitsTemplate template, TraitTypeList traitTypelist) {
    GroupedTraitTypeBuilder builder = new GroupedTraitTypeBuilder(traitTypelist);
    for (Group group : template.groups) {
      for (String traitId : group.traits) {
        builder.addGroupedTraitType(traitId, group.id);
      }
    }
    return builder.getTraitTypeGroups();
  }

  private List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final TraitTypeList traitTypeList;

  public GroupedTraitTypeBuilder(TraitTypeList traitTypelist) {
    this.traitTypeList = traitTypelist;
  }

  public GroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedTraitType(String traitId, String groupId) {
    TraitType traitType = traitTypeList.getById(traitId);
    GroupedTraitType type = new GroupedTraitType(traitType, groupId);
    groupedTraitTypes.add(type);
  }
}