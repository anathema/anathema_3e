package net.sf.anathema.hero.traits.model.group;

import net.sf.anathema.hero.elsewhere.concept.CasteCollection;
import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.hero.traits.model.lists.IdentifiedCasteTraitTypeList;
import net.sf.anathema.library.collection.MultiEntryMap;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTraitTypeGroupFactory {

  protected abstract Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId);

  public IIdentifiedCasteTraitTypeList[] createTraitGroups(CasteCollection casteCollection, GroupedTraitType[] traitTypes) {
    Set<String> groupIds = new LinkedHashSet<>();
    MultiEntryMap<String, TraitType> traitTypesByGroupId = new MultiEntryMap<>();
    for (GroupedTraitType type : traitTypes) {
      String groupId = type.getGroupId();
      groupIds.add(groupId);
      traitTypesByGroupId.add(groupId, type.getTraitType());
    }
    List<IIdentifiedCasteTraitTypeList> groups = new ArrayList<>();
    for (String groupId : groupIds) {
      List<TraitType> groupTraitTypes = traitTypesByGroupId.get(groupId);
      MultiEntryMap<TraitType, CasteType> castesByTrait = new MultiEntryMap<>();
      for (GroupedTraitType type : traitTypes) {
        if (!type.getGroupId().equals(groupId)) {
          continue;
        }
        CasteType[] favoring = casteCollection.getWithFavoredTrait(type.getTraitType().getId());
        castesByTrait.add(type.getTraitType(), favoring);
      }
      Identifier groupIdentifier = getGroupIdentifier(casteCollection, groupId);
      TraitType[] types = groupTraitTypes.toArray(new TraitType[groupTraitTypes.size()]);
      groups.add(new IdentifiedCasteTraitTypeList(types, groupIdentifier, castesByTrait));
    }
    return groups.toArray(new IIdentifiedCasteTraitTypeList[groups.size()]);
  }
}