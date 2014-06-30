package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;

public interface MartialArtsLearnModel {

  Charm[] getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}