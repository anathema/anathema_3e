package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.hero.charms.model.options.NonMartialArtsCharmTreeCategoryImpl;
import net.sf.anathema.hero.charms.model.options.MartialArtsCharmTreeCategory;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CascadeGroupCollection implements CharmTreeCollection {
  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;
  private CharmTreeIdentifierMap treeIdentifierMap;

  public CascadeGroupCollection(CharmProvider charmProvider, CharacterTypes characterTypes, CharmTreeIdentifierMap treeIdentifierMap) {
    this.charmProvider = charmProvider;
    this.treeIdentifierMap = treeIdentifierMap;
    this.characterTypes = characterTypes;
  }

  @Override
  public CharmTree[] getAllCharmTrees() {
    List<CharmTree> allCharmGroups = new ArrayList<>();
    initCharacterTypeCharms(allCharmGroups);
    initMartialArtsCharms(allCharmGroups);
    return allCharmGroups.toArray(new CharmTree[allCharmGroups.size()]);
  }

  private void initCharacterTypeCharms(List<CharmTree> allCharmGroups) {
    for (CharacterType type : characterTypes) {
       if (charmProvider.getCharms(type).length > 0) {
        registerTypeCharms(allCharmGroups, type);
      }
    }
  }

  private void initMartialArtsCharms(List<CharmTree> allCharmGroups) {
    CharmTreeCategory martialArtsTree = new MartialArtsCharmTreeCategory(charmProvider, MartialArtsLevel.Sidereal);
    treeIdentifierMap.put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
    allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmTrees()));
  }

  private void registerTypeCharms(List<CharmTree> allCharmGroups, CharacterType type) {
    CharmTreeCategory typeTree = new NonMartialArtsCharmTreeCategoryImpl(charmProvider, type);
    registerGroups(allCharmGroups, type, typeTree);
  }

  private void registerGroups(List<CharmTree> allCharmGroups, Identifier typeId, CharmTreeCategory charmTreeCategory) {
    CharmTree[] groups = charmTreeCategory.getAllCharmTrees();
    if (groups.length != 0) {
      treeIdentifierMap.put(typeId, charmTreeCategory);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }
}