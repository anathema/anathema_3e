package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.BasicLearningModel;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(BasicLearningModel group, Charm charm) {
    return group.isLearned(charm, true);
  }

  @Override
  public boolean isLearned(BasicLearningModel group, Charm charm) {
    return group.isLearned(charm, false) || group.isLearned(charm, true);
  }

  @Override
  public void toggleLearned(BasicLearningModel group, Charm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}