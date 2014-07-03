package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;

import java.util.Collection;

public class GroupedCharmMap implements CharmMap {

  private final Collection<? extends CharmMap> trees;

  public GroupedCharmMap(Collection<? extends CharmMap> trees) {
    this.trees = trees;
  }

  @Override
  public Charm getCharmById(CharmName charmId) {
    for (CharmMap tree : trees) {
      Charm charm = tree.getCharmById(charmId);
      if (charm != null) {
        return charm;
      }
    }
    return null;
  }
}