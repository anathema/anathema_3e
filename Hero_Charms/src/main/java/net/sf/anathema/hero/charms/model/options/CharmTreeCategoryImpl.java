package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategoryReference;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.lib.util.Identifier;

import java.util.*;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.getTreeCategory;

public final class CharmTreeCategoryImpl implements CharmTreeCategory {

  public static CharmTreeCategory ForMartialArts(CharmOptionCheck check, CharmProvider provider) {
    Identifier treeIdentifier = MARTIAL_ARTS;
    TreeCategoryReference treeCategoryReference = getTreeCategory(treeIdentifier);
    Charm[] charms = provider.getCharms(treeCategoryReference);
    return new CharmTreeCategoryImpl(check, charms, treeCategoryReference);
  }

  public static CharmTreeCategory ForNonMartialArts(CharmOptionCheck check, CharmProvider provider, CharacterType characterType) {
    Identifier treeIdentifier = characterType;
    TreeCategoryReference treeCategoryReference = getTreeCategory(treeIdentifier);
    Charm[] charms = provider.getCharms(treeCategoryReference);
    return new CharmTreeCategoryImpl(check, charms, treeCategoryReference);
  }

  public static CharmTreeCategory For(CharmOptionCheck check, CharmProvider charmProvider, TreeCategoryReference category) {
    Charm[] charms = charmProvider.getCharms(category);
    return new CharmTreeCategoryImpl(check, charms, category);
  }

  private final Map<String, Charm> charmById = new HashMap<>();
  private CharmOptionCheck optionCheck;
  private Charm[] allCharms;
  private TreeCategoryReference category;

  public CharmTreeCategoryImpl(CharmOptionCheck optionCheck, Charm[] allCharms, TreeCategoryReference category) {
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

  @Override
  public TreeCategoryReference getReference() {
    return category;
  }
}
