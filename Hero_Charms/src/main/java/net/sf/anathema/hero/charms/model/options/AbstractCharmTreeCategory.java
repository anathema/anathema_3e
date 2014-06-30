package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.*;

public abstract class AbstractCharmTreeCategory implements CharmTreeCategory {

  private final Map<String, Charm> charmById = new HashMap<>();
  private Charm[] allCharms;
  private TreeCategory category;

  public AbstractCharmTreeCategory(Charm[] allCharms, TreeCategory category) {
    this.allCharms = allCharms;
    this.category = category;
    for (Charm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  @Override
  public final Charm getCharmById(String charmID) {
    return charmById.get(charmID);
  }

  public final Charm[] getAllCharms() {
    return allCharms;
  }

  private final void addCharmGroupsFor(Collection<String> groupIds, List<CharmTree> charmTrees, Charm[] charms) {
    for (Charm charm : charms) {
      String treeName = charm.getGroupId();
      if (!groupIds.contains(treeName) && isLearnable(charm)) {
        groupIds.add(treeName);
        List<Charm> treeCharms = getAllCharmsForGroup(treeName);
        CharacterType characterType = charm.getCharacterType();
        Charm[] charmArray = treeCharms.toArray(new Charm[treeCharms.size()]);
        charmTrees.add(new CharmTreeImpl(characterType, treeName, charmArray));
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
}
