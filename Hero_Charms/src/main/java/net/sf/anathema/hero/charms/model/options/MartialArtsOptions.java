package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.isMartialArts;

public class MartialArtsOptions implements CharmIdMap, ICharmLearnableArbitrator {

  private final MartialArtsCharmTreeCategory martialArtsCharmTree;

  public MartialArtsOptions(CharmProvider charmProvider, CharmsRules charmsRules) {
    MartialArtsLevel standardLevel = charmsRules.getMartialArtsRules().getStandardLevel();
    this.martialArtsCharmTree = new MartialArtsCharmTreeCategory(charmProvider, standardLevel);
  }

  @Override
  public Charm getCharmById(String charmId) {
    return martialArtsCharmTree.getCharmById(charmId);
  }

  public CharmTree[] getAllCharmGroups() {
    return martialArtsCharmTree.getAllCharmTrees();
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return !isMartialArts(charm) || martialArtsCharmTree.isLearnable(charm);
  }
}
