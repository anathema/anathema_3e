package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CharmTreeCategoryImpl implements CharmTreeCategory {

  private final Map<String, Charm> charmById = new HashMap<>();
  private Charm[] allCharms;

  public CharmTreeCategoryImpl(Charm[] charms) {
    this.allCharms = charms;
    for (Charm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  @Override
  public Charm getCharmById(String charmID) {
    return charmById.get(charmID);
  }

  public Charm[] getAllCharms() {
    return allCharms;
  }

  private void addCharmGroupsFor(Collection<String> groupIds, List<CharmTree> charmGroups, Charm[] charms) {
    for (Charm charm : charms) {
      String groupId = charm.getGroupId();
      if (!groupIds.contains(groupId) && isLearnable(charm)) {
        groupIds.add(groupId);
        List<Charm> groupCharms = getAllCharmsForGroup(groupId);
        charmGroups.add(new CharmTreeImpl(charm.getCharacterType(), groupId, groupCharms.toArray(new Charm[groupCharms.size()])
        ));
      }
    }
  }

  @Override
  public final CharmTree[] getAllCharmTrees() {
    Set<String> charmGroupSet = new HashSet<>();
    List<CharmTree> charmGroups = new ArrayList<>();
    addCharmGroupsFor(charmGroupSet, charmGroups, getAllCharms());
    return charmGroups.toArray(new CharmTree[charmGroups.size()]);
  }

  public final List<Charm> getAllCharmsForGroup(String id) {
    List<Charm> groupCharms = new ArrayList<>();
    for (Charm charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return true;
  }
}