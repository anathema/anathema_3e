package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.GroupedCharmIdMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.isMartialArts;

public class CharmOptions {
  private NonMartialArtsOptions nonMartialArtsOptions;
  private final MartialArtsCharmTreeCategory martialArtsCharmTree;
  private CharmProvider charmProvider;

  public CharmOptions(CharmProvider charmProvider, CharmsRules charmsRules, Hero hero, CharacterTypes characterTypes) {
    this.charmProvider = charmProvider;
    MartialArtsLevel standardLevel = charmsRules.getMartialArtsRules().getStandardLevel();
    this.martialArtsCharmTree = new MartialArtsCharmTreeCategory(charmProvider, standardLevel);
    this.nonMartialArtsOptions = new NonMartialArtsOptions(hero, characterTypes, charmProvider, charmsRules);
  }

  public CharmTree[] getAllMartialArtsTrees() {
    return martialArtsCharmTree.getAllCharmTrees();
  }

  public Iterable<CharacterType> getAvailableCharacterTypes() {
    return nonMartialArtsOptions.getAvailableCharacterTypes();
  }

  public CharmTree[] getAllTreesForType(CharacterType characterType) {
    return nonMartialArtsOptions.getCharmTrees(characterType).getAllCharmTrees();
  }

  public CharmIdMap getCharmIdMap() {
    List<CharmIdMap> trees = new ArrayList<>();
    trees.add(nonMartialArtsOptions);
    trees.add(martialArtsCharmTree);
    return new GroupedCharmIdMap(trees);
  }

  public ISpecialCharm[] getSpecialCharms() {
    ICharmLearnableArbitrator arbitrator = charm -> !isMartialArts(charm) || martialArtsCharmTree.isLearnable(charm);
    return charmProvider.getSpecialCharms(arbitrator, getCharmIdMap(), nonMartialArtsOptions.getNativeCharacterType());
  }

  public boolean isAlienType(CharacterType characterType) {
    return nonMartialArtsOptions.isAlienType(characterType);
  }

  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    return nonMartialArtsOptions.getCharacterTypes(includeAlienTypes);
  }

  public boolean isAlienCharm(Charm charm) {
    boolean isNotMartialArts = !isMartialArts(charm);
    boolean isOfAlienType = nonMartialArtsOptions.isAlienType(charm.getCharacterType());
    return isNotMartialArts && isOfAlienType;
  }

  public Charm[] getCharms(CharmTree tree) {
    return nonMartialArtsOptions.getCharms(tree);
  }
}
