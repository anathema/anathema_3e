package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CascadeGroupCollection implements CharmTreeCollection {
  private CharmProvider charmProvider;
  private CharmTreeMap treeIdentifierMap;

  public CascadeGroupCollection(CharmProvider charmProvider, CharmTreeMap treeIdentifierMap) {
    this.charmProvider = charmProvider;
    this.treeIdentifierMap = treeIdentifierMap;
  }

  @Override
  public boolean isEmpty() {
    return treeIdentifierMap.isEmpty();
  }

  @Override
  public Collection<CharmTree> getAllCharmTrees() {
    List<CharmTree> allCharmGroups = new ArrayList<>();
    for (CategoryReference category : charmProvider.getAllCategories()) {
    	CharmTreeCategory treeCategory = new CharmTreeCategoryImpl(new GreedyCharmOptionCheck(),
    			charmProvider.getCharms(category), category);
    	treeIdentifierMap.put(category, treeCategory);
    }
    return allCharmGroups;
  }
}