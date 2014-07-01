package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.*;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;

public final class CharmTreeCategoryImpl implements CharmTreeCategory {

  public static CharmTreeCategory ForMartialArts(CharmOptionCheck check, CharmProvider provider) {
    Charm[] charms = provider.getMartialArtsCharms();
    TreeCategory treeCategory = new TreeCategory(MARTIAL_ARTS.getId());
    return new CharmTreeCategoryImpl(check, charms, treeCategory);
  }

  public static CharmTreeCategory ForNonMartialArts(CharmOptionCheck check, CharmProvider provider, CharacterType characterType) {
    Charm[] charms = provider.getCharms(characterType);
    TreeCategory treeCategory = new TreeCategory(characterType.getId());
    return new CharmTreeCategoryImpl(check, charms, treeCategory);
  }

  private final Map<String, Charm> charmById = new HashMap<>();
  private CharmOptionCheck optionCheck;
  private Charm[] allCharms;
  private TreeCategory category;

  public CharmTreeCategoryImpl(CharmOptionCheck optionCheck, Charm[] allCharms, TreeCategory category) {
    this.optionCheck = optionCheck;
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
      if (!treeNameList.contains(treeName) && optionCheck.isValidOptionForHeroesType(charm)) {
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
