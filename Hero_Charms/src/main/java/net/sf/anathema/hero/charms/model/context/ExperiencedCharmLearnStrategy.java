package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.BasicLearningModel;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isForgettable(BasicLearningModel group, Charm charm) {
    return group.isLearnedOnCreation(charm);
  }

  @Override
  public boolean isLearned(BasicLearningModel group, Charm charm) {
    return group.isLearnedOnCreation(charm) || group.isLearnedWithExperience(charm);
  }

  @Override
  public void toggleLearned(BasicLearningModel group, Charm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}