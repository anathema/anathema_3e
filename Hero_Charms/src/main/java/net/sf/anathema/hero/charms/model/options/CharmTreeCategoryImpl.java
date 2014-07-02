package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeImpl;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;

public final class CharmTreeCategoryImpl implements CharmTreeCategory {

  public static CharmTreeCategory ForMartialArts(CharmOptionCheck check, CharmProvider provider) {
    CategoryReference categoryReference = MartialArtsUtilities.getCategory(MARTIAL_ARTS);
    Charm[] charms = provider.getCharms(categoryReference);
    return new CharmTreeCategoryImpl(check, charms, categoryReference);
  }

  public static CharmTreeCategory ForNonMartialArts(CharmOptionCheck check, CharmProvider provider, CharacterType characterType) {
    CategoryReference categoryReference = MartialArtsUtilities.getCategory(characterType);
    Charm[] charms = provider.getCharms(categoryReference);
    return new CharmTreeCategoryImpl(check, charms, categoryReference);
  }

  public static CharmTreeCategory CreateFor(CharmOptionCheck check, CharmProvider provider, CategoryReference reference) {
    Charm[] charms = provider.getCharms(reference);
    return new CharmTreeCategoryImpl(check, charms, reference);
  }

  private final Map<CharmName, Charm> charmById = new HashMap<>();
  private CharmOptionCheck optionCheck;
  private Charm[] allCharms;
  private CategoryReference category;

  public CharmTreeCategoryImpl(CharmOptionCheck optionCheck, Charm[] allCharms, CategoryReference category) {
    this.optionCheck = optionCheck;
    this.allCharms = allCharms;
    this.category = category;
    for (Charm charm : allCharms) {
      charmById.put(charm.getName(), charm);
    }
  }

  @Override
  public final Charm getCharmById(CharmName charmID) {
    return charmById.get(charmID);
  }

  public final Charm[] getAllCharms() {
    return allCharms;
  }

  private void addCharmTreesFor(Collection<TreeName> treeNameList, List<CharmTree> treeList, Charm[] charms) {
    for (Charm charm : charms) {
      TreeName treeName = charm.getTreeReference().name;
      if (!treeNameList.contains(treeName) && optionCheck.isValidOptionForHeroType(charm)) {
        treeNameList.add(treeName);
        List<Charm> treeCharms = getAllCharmsForTree(treeName);
        Charm[] charmArray = treeCharms.toArray(new Charm[treeCharms.size()]);
        treeList.add(new CharmTreeImpl(new TreeReference(category, treeName), charmArray));
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return allCharms.length == 0;
  }

  @Override
  public final CharmTree[] getAllCharmTrees() {
    Set<TreeName> treeNameList = new HashSet<>();
    List<CharmTree> treeList = new ArrayList<>();
    addCharmTreesFor(treeNameList, treeList, getAllCharms());
    return treeList.toArray(new CharmTree[treeList.size()]);
  }

  public final List<Charm> getAllCharmsForTree(TreeName treeName) {
    List<Charm> groupCharms = new ArrayList<>();
    for (Charm charm : getAllCharms()) {
      if (charm.getTreeReference().name.equals(treeName)) {
        groupCharms.add(charm);
      }
    }
    return groupCharms;
  }

  @Override
  public CategoryReference getReference() {
    return category;
  }
}
