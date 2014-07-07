package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CascadeGroupCollection implements CharmTreeCollection {
  private final HeroTypes heroTypes;
  private CharmProvider charmProvider;
  private CharmTreeMap treeIdentifierMap;

  public CascadeGroupCollection(CharmProvider charmProvider, HeroTypes heroTypes, CharmTreeMap treeIdentifierMap) {
    this.charmProvider = charmProvider;
    this.treeIdentifierMap = treeIdentifierMap;
    this.heroTypes = heroTypes;
  }

  @Override
  public boolean isEmpty() {
    return treeIdentifierMap.isEmpty();
  }

  @Override
  public Collection<CharmTree> getAllCharmTrees() {
    List<CharmTree> allCharmGroups = new ArrayList<>();
    initCharacterTypeCharms(allCharmGroups);
    initMartialArtsCharms(allCharmGroups);
    return allCharmGroups;
  }

  private void initCharacterTypeCharms(List<CharmTree> allCharmGroups) {
    for (HeroType type : heroTypes) {
       if (!charmProvider.getCharms(MartialArtsUtilities.getCategory(type)).isEmpty()) {
        registerTypeCharms(allCharmGroups, type);
      }
    }
  }

  private void initMartialArtsCharms(List<CharmTree> allCharmGroups) {
    CharmTreeCategory martialArtsTree = CharmTreeCategoryImpl.ForMartialArts(new GreedyCharmOptionCheck(), charmProvider);
    treeIdentifierMap.put(MartialArtsUtilities.getCategory(MartialArtsUtilities.MARTIAL_ARTS), martialArtsTree);
    allCharmGroups.addAll(martialArtsTree.getAllCharmTrees());
  }

  private void registerTypeCharms(List<CharmTree> allCharmGroups, HeroType type) {
    CharmTreeCategory typeTree = CharmTreeCategoryImpl.ForNonMartialArts(new GreedyCharmOptionCheck(), charmProvider, type);
    registerGroups(allCharmGroups, MartialArtsUtilities.getCategory(type), typeTree);
  }

  private void registerGroups(List<CharmTree> allCharmGroups, CategoryReference typeId, CharmTreeCategory charmTreeCategory) {
    Collection<CharmTree> groups = charmTreeCategory.getAllCharmTrees();
    if (!groups.isEmpty()) {
      treeIdentifierMap.put(typeId, charmTreeCategory);
      allCharmGroups.addAll(groups);
    }
  }

}