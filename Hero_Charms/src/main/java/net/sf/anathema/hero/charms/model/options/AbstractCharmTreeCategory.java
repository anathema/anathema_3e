package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

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

  private final void addCharmTreesFor(Collection<TreeName> treeNameList, List<CharmTree> treeList, Charm[] charms) {
    for (Charm charm : charms) {
      TreeName treeName = new TreeName(charm.getGroupId());
      if (!treeNameList.contains(treeName) && isLearnable(charm)) {
        treeNameList.add(treeName);
        List<Charm> treeCharms = getAllCharmsForTree(treeName.text);
        Charm[] charmArray = treeCharms.toArray(new Charm[treeCharms.size()]);
        treeList.add(new CharmTreeImpl(new TreeReference(category, treeName), charmArray));
      }
    }
  }

  @Override
  public final CharmTree[] getAllCharmTrees() {
    Set<TreeName> treeNameList = new HashSet<>();
    List<CharmTree> treeList = new ArrayList<>();
    addCharmTreesFor(treeNameList, treeList, getAllCharms());
    return treeList.toArray(new CharmTree[treeList.size()]);
  }

  public final List<Charm> getAllCharmsForTree(String id) {
    List<Charm> groupCharms = new ArrayList<>();
    for (Charm charm : getAllCharms()) {
      if (charm.getGroupId().equals(id)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }
}
