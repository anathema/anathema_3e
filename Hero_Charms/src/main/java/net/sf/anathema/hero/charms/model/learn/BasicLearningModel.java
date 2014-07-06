package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface BasicLearningModel {

  boolean isCurrentlyLearned(Charm charm);

  boolean isLearnedOnCreation(Charm charm);

  boolean isLearnedWithExperience(Charm charm);

  void toggleLearnedOnCreation(Charm charm);

  void toggleExperienceLearnedCharm(Charm charm);
}