package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

import java.util.Set;

public interface MartialArtsLearnModel {

  Set<Charm> getLearnedCharms();

  String[] getIncompleteCelestialMartialArtsGroups();

  String[] getCompleteCelestialMartialArtsGroups();

  boolean isAnyCelestialStyleCompleted();
}