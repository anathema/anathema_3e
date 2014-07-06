package net.sf.anathema.hero.traits.model.group;

import net.sf.anathema.hero.concept.model.concept.CasteCollection;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.traits.model.lists.IdentifiedTraitTypeListImpl;
import net.sf.anathema.library.collection.MultiEntryMap;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId);

  public IdentifiedTraitTypeList[] createTraitGroups(CasteCollection casteCollection, GroupedTraitType[] traitTypes) {
    Set<String> groupIds = new LinkedHashSet<>();
    MultiEntryMap<String, TraitType> traitTypesByGroupId = new MultiEntryMap<>();
    for (GroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      groupIds.add(groupId);
      traitTypesByGroupId.add(groupId, type.getTraitType());
    }
    List<IdentifiedTraitTypeList> groups = new ArrayList<>();
    for (String groupId : groupIds) {
      List<TraitType> groupTraitTypes = traitTypesByGroupId.get(groupId);
      for (GroupedTraitType type : traitTypes) {
        if (!type.getGroupId().equals(groupId)) {
          continue;
        }
      }
      Identifier groupIdentifier = getGroupIdentifier(casteCollection, groupId);
      TraitType[] types = groupTraitTypes.toArray(new TraitType[groupTraitTypes.size()]);
      groups.add(new IdentifiedTraitTypeListImpl(types, groupIdentifier));
    }
    return groups.toArray(new IdentifiedTraitTypeList[groups.size()]);
  }
}