package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.spells.data.CircleType;

import java.util.Collection;

public interface SorceryInitiation {

  boolean isInitiated(CircleType circle);

  boolean canInitiate();

  Collection<CircleType> getCirclesToInitiateInto();
}
