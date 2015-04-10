package net.sf.anathema.hero.traits.model.group;

import com.google.common.collect.LinkedHashMultimap;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeListImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TraitTypeGroupFactory {

  public IdentifiedTraitTypeList[] createTraitGroups(GroupedTraitType[] traitTypes) {
    LinkedHashMultimap<GroupName, TraitType> traitTypesByGroupId = LinkedHashMultimap.create();
    for (GroupedTraitType type : traitTypes) {
      traitTypesByGroupId.put(type.getGroupName(), type.getTraitType());
    }
    List<IdentifiedTraitTypeList> groups = new ArrayList<>();
    for (GroupName group : traitTypesByGroupId.keySet()) {
      Set<TraitType> groupTraitTypes = traitTypesByGroupId.get(group);
      TraitType[] types = groupTraitTypes.toArray(new TraitType[groupTraitTypes.size()]);
      groups.add(new IdentifiedTraitTypeListImpl(types, group));
    }
    return groups.toArray(new IdentifiedTraitTypeList[groups.size()]);
  }
}