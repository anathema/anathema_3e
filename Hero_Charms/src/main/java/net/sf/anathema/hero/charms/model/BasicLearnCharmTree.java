package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;

public interface BasicLearnCharmTree {

  boolean isLearned(Charm charm);

  boolean isLearned(Charm charm, boolean experienced);

  void toggleLearnedOnCreation(Charm charm);

  void toggleExperienceLearnedCharm(Charm charm);
}